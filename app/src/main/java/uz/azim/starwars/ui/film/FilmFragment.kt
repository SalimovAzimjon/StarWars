package uz.azim.starwars.ui.film

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import uz.azim.starwars.App
import uz.azim.starwars.R
import uz.azim.starwars.databinding.FragmentFilmsBinding
import uz.azim.starwars.di.film.FilmComponent
import uz.azim.starwars.domain.model.Film
import uz.azim.starwars.ui.film.adapter.FilmAdapter
import uz.azim.starwars.utils.Resource
import uz.azim.starwars.utils.ViewModelFactory
import uz.azim.starwars.utils.unsafeLazy
import javax.inject.Inject

class FilmFragment : Fragment(R.layout.fragment_characters) {

    private val args by navArgs<FilmFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by viewBinding(FragmentFilmsBinding::bind)
    private val viewModel by viewModels<FilmViewModel> { viewModelFactory }
    private val filmAdapter by unsafeLazy { FilmAdapter(::onFilmClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FilmComponent.create((requireActivity().application as App).provideAppComponent())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.getFilm(args.filmUrls)
        }
        setupUi()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.films.collect { data ->
                    handleFilm(data)
                }
            }
        }
    }

    private fun setupUi() {
        binding.rvFilms.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvFilms.adapter = filmAdapter
    }

    private fun onFilmClicked(film: Film) {}

    private fun handleFilm(data: Resource<List<Film>>) {
        when (data) {
            is Resource.Success -> {
                filmAdapter.submitList(data.data)
            }
        }
    }

}
