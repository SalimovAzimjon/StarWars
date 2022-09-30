package uz.azim.starwars.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import uz.azim.starwars.data.network.CharactersService
import uz.azim.starwars.data.network.FilmService
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class, BaseModule::class]
)
interface AppComponent {

    companion object {
        fun create(context: Context): AppComponent {
            return DaggerAppComponent.builder().context(context).build()
        }
    }

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }

    fun provideCharacterService(): CharactersService
    fun provideFilmService(): FilmService
    fun provideIoDispatcher(): CoroutineDispatcher

}
