package uz.azim.starwars.di.film

import dagger.Component
import uz.azim.starwars.di.AppComponent
import uz.azim.starwars.ui.film.FilmFragment

@FilmScope
@Component(
    dependencies = [AppComponent::class],
    modules = [FilmModule::class, FilmViewModelModule::class]
)
interface FilmComponent {

    companion object {
        fun create(appComponent: AppComponent): FilmComponent {
            return DaggerFilmComponent.builder().appComponent(appComponent).build()
        }
    }

    fun inject(filmFragment: FilmFragment)
}
