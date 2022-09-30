package uz.azim.starwars.data.repository.film

import kotlinx.coroutines.flow.Flow
import uz.azim.starwars.domain.model.Film
import uz.azim.starwars.utils.Resource

interface FilmRepository {

    fun getFilms(url: List<String>): Flow<Resource<List<Film>>>

}
