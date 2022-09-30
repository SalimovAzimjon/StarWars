package uz.azim.starwars.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import uz.azim.starwars.data.network.CharactersService
import uz.azim.starwars.data.network.FilmService
import javax.inject.Singleton

@Module
object ServiceModule {

    @Singleton
    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharactersService {
        return retrofit.create(CharactersService::class.java)
    }

    @Singleton
    @Provides
    fun provideFilmService(retrofit: Retrofit): FilmService {
        return retrofit.create(FilmService::class.java)
    }

}
