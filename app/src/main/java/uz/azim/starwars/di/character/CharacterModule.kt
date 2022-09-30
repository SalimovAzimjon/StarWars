package uz.azim.starwars.di.character

import androidx.paging.PagingSource
import dagger.Binds
import dagger.Module
import uz.azim.starwars.data.repository.character.CharacterPagingSource
import uz.azim.starwars.domain.model.Character
import uz.azim.starwars.domain.usecase.character.GetCharacterUseCase
import uz.azim.starwars.domain.usecase.character.GetCharacterUseCaseImpl

@Module
interface CharacterModule {

    @CharacterScope
    @Binds
    fun bindCharacterPagingSource(characterPagingSource: CharacterPagingSource): PagingSource<String, Character>

    @CharacterScope
    @Binds
    fun bindCharacterUseCase(getCharacterUseCaseImpl: GetCharacterUseCaseImpl): GetCharacterUseCase

}
