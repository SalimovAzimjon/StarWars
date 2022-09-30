package uz.azim.starwars.di.character

import dagger.Component
import uz.azim.starwars.di.AppComponent
import uz.azim.starwars.domain.usecase.character.GetCharacterUseCase
import uz.azim.starwars.ui.characters.CharactersFragment

@CharacterScope
@Component(
    dependencies = [AppComponent::class],
    modules = [CharacterModule::class, CharacterViewModelModule::class]
)
interface CharacterComponent {

    companion object {
        fun create(appComponent: AppComponent): CharacterComponent {
            return DaggerCharacterComponent.builder().appComponent(appComponent).build()
        }
    }

    fun inject(charactersFragment: CharactersFragment)

}
