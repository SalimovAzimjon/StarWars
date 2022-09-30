package uz.azim.starwars.domain.usecase.film

import kotlinx.coroutines.flow.Flow
import uz.azim.starwars.data.repository.film.FilmRepository
import uz.azim.starwars.domain.model.Film
import uz.azim.starwars.utils.Resource
import javax.inject.Inject

class GetFilmUseCaseImpl @Inject constructor(
    private val filmRepository: FilmRepository
) : GetFilmUseCase {

    override fun execute(url: List<String>): Flow<Resource<List<Film>>> {
        return filmRepository.getFilms(url)
    }

}
