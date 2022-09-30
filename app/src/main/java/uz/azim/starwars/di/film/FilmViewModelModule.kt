package uz.azim.starwars.di.film

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.azim.starwars.di.ViewModelKey
import uz.azim.starwars.ui.characters.CharactersViewModel
import uz.azim.starwars.ui.film.FilmViewModel
import uz.azim.starwars.utils.ViewModelFactory

@Module
interface FilmViewModelModule {

    @FilmScope
    @ViewModelKey(FilmViewModel::class)
    @IntoMap
    @Binds
    fun bindFilmViewModel(viewModel: FilmViewModel): ViewModel

}
