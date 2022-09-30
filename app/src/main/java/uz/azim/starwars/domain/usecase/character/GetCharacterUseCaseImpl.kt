package uz.azim.starwars.domain.usecase.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.azim.starwars.data.repository.character.CharacterPagingSource
import uz.azim.starwars.domain.model.Character
import javax.inject.Inject

class GetCharacterUseCaseImpl @Inject constructor(
    private val characterPagingSource: CharacterPagingSource
) : GetCharacterUseCase {

    private val pageSize = 10

    override fun execute(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false)
        ) { characterPagingSource }.flow
    }

}
