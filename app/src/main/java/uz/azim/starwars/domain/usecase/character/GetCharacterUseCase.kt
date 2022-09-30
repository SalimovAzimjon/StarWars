package uz.azim.starwars.domain.usecase.character

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.azim.starwars.domain.model.Character
import uz.azim.starwars.utils.Resource

interface GetCharacterUseCase {

    fun execute(): Flow<PagingData<Character>>

}
