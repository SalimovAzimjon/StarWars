package uz.azim.starwars.di.film

import dagger.Binds
import dagger.Module
import uz.azim.starwars.data.repository.film.FilmRepository
import uz.azim.starwars.data.repository.film.FilmRepositoryImpl
import uz.azim.starwars.domain.usecase.film.GetFilmUseCase
import uz.azim.starwars.domain.usecase.film.GetFilmUseCaseImpl

@Module
interface FilmModule {

    @FilmScope
    @Binds
    fun bindFilmRepository(filmRepositoryImpl: FilmRepositoryImpl): FilmRepository

    @FilmScope
    @Binds
    fun bindGetFilmUseCase(getFilmUseCaseImpl: GetFilmUseCaseImpl): GetFilmUseCase

}
