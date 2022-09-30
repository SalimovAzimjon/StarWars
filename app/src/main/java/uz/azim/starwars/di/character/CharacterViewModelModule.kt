package uz.azim.starwars.di.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.azim.starwars.di.ViewModelKey
import uz.azim.starwars.ui.characters.CharactersViewModel
import uz.azim.starwars.utils.ViewModelFactory

@Module
interface CharacterViewModelModule {

    @CharacterScope
    @ViewModelKey(CharactersViewModel::class)
    @IntoMap
    @Binds
    fun bindCharacterViewModel(viewModel: CharactersViewModel): ViewModel

}
