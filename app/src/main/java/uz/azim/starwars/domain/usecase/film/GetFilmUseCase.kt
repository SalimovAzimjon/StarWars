package uz.azim.starwars.domain.usecase.film

import kotlinx.coroutines.flow.Flow
import uz.azim.starwars.domain.model.Film
import uz.azim.starwars.utils.Resource

interface GetFilmUseCase {

    fun execute(url: List<String>): Flow<Resource<List<Film>>>

}
