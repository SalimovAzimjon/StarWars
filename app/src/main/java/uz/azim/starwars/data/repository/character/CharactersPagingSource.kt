package uz.azim.starwars.data.repository.character

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.azim.starwars.data.models.CharacterResponse
import uz.azim.starwars.data.models.PagingResponse
import uz.azim.starwars.data.network.CharactersService
import uz.azim.starwars.domain.model.Character
import uz.azim.starwars.domain.model.CharacterType
import uz.azim.starwars.mapper.getCharacterTypeFromClassification
import uz.azim.starwars.mapper.toEntity
import uz.azim.starwars.utils.handleResponse
import javax.inject.Inject

private const val START = 1

class CharacterPagingSource @Inject constructor(
    private val charactersService: CharactersService
) : PagingSource<String, Character>() {

    override fun getRefreshKey(state: PagingState<String, Character>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Character> {
        return try {
            val character = getCharacterFromApi(params.key)
            val charactersList = arrayListOf<Character>()
            character.results.forEach {
                charactersList.add(convertResponseToEntity(it))
            }
            charactersList.toList()
            LoadResult.Page(
                data = charactersList,
                prevKey = character.previous,
                nextKey = character.next
            )
        } catch (e: Exception) {
            LoadResult.Error(Throwable(e.message))
        }
    }

    private suspend fun getCharacterFromApi(paramKey: String?): PagingResponse<CharacterResponse> {
        return if (paramKey == null)
            charactersService.getCharacters(START).handleResponse()
        else
            charactersService.getCharacters(paramKey).handleResponse()
    }

    private suspend fun convertResponseToEntity(character: CharacterResponse): Character {
        return if (character.species.isEmpty()) {
            character.toEntity(CharacterType.HUMAN)
        } else {
            val characterSpeciesResponse =
                charactersService.getSpecies(character.species.first()).handleResponse()
            character.toEntity(
                getCharacterTypeFromClassification(characterSpeciesResponse.classification)
            )
        }
    }

}
