package uz.azim.starwars.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import uz.azim.starwars.domain.model.Character
import uz.azim.starwars.domain.usecase.character.GetCharacterUseCase
import uz.azim.starwars.utils.Resource
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _characters = MutableStateFlow<Resource<PagingData<Character>>>(Resource.Idle())
    val character = _characters.asStateFlow()

    fun getCharacters() = viewModelScope.launch {
        getCharacterUseCase.execute()
            .cachedIn(viewModelScope)
            .onStart { _characters.emit(Resource.Loading()) }
            .catch { _characters.emit(Resource.Error(it)) }
            .collect { _characters.emit(Resource.Success(it)) }
    }

}
