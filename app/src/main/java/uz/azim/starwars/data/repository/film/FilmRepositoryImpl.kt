package uz.azim.starwars.data.repository.film

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.azim.starwars.data.network.FilmService
import uz.azim.starwars.domain.model.Film
import uz.azim.starwars.mapper.toEntity
import uz.azim.starwars.utils.Resource
import uz.azim.starwars.utils.handleResponse
import uz.azim.starwars.utils.safeFlow
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val filmService: FilmService,
    private val ioDispatcher: CoroutineDispatcher
) : FilmRepository {

    override fun getFilms(url: List<String>): Flow<Resource<List<Film>>> = safeFlow {
        val films = arrayListOf<Film>()
        url.forEach {
            val filmsResponse = filmService.getFilm(it).handleResponse()
            films.add(filmsResponse.toEntity())
        }
        films.toList()
    }.flowOn(ioDispatcher)

}
