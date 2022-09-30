package uz.azim.starwars.ui.film

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.azim.starwars.domain.model.Film
import uz.azim.starwars.domain.usecase.film.GetFilmUseCase
import uz.azim.starwars.utils.Resource
import javax.inject.Inject

class FilmViewModel @Inject constructor(
    private val getFilmUseCase: GetFilmUseCase
) : ViewModel() {

    private val _films = MutableStateFlow<Resource<List<Film>>>(Resource.Idle())
    val films = _films.asStateFlow()

    fun getFilm(urls: Array<String>) = viewModelScope.launch {
        getFilmUseCase.execute(urls.toList())
            .collect { _films.emit(it) }
    }

}
