package uz.azim.starwars.ui.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import uz.azim.starwars.App
import uz.azim.starwars.R
import uz.azim.starwars.databinding.FragmentCharactersBinding
import uz.azim.starwars.di.character.CharacterComponent
import uz.azim.starwars.domain.model.Character
import uz.azim.starwars.ui.characters.adapter.CharactersAdapter
import uz.azim.starwars.utils.Resource
import uz.azim.starwars.utils.ViewModelFactory
import uz.azim.starwars.utils.unsafeLazy
import javax.inject.Inject

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<CharactersViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentCharactersBinding::bind)
    private val charactersAdapter by unsafeLazy { CharactersAdapter(::onCharacterClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CharacterComponent.create((requireActivity().application as App).provideAppComponent())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacters()
        setUpUi()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.character.collect { data ->
                    handleCharacters(data)
                }
            }
        }
    }

    private fun setUpUi() {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvCharacters.layoutManager = layoutManager
        binding.rvCharacters.adapter = charactersAdapter
    }

    private fun onCharacterClicked(character: Character) {
        val action =
            CharactersFragmentDirections.actionCharactersFragmentToFilmFragment(character.films.toTypedArray())
        findNavController().navigate(action)
    }

    private suspend fun handleCharacters(data: Resource<PagingData<Character>>) {
        when (data) {
            is Resource.Loading -> {
                //TODO Loading logic
            }
            is Resource.Success ->
                charactersAdapter.submitData(data.data)
            is Resource.Error -> {
                //TODO Error logic
            }
        }
    }

}
