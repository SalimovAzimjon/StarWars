package uz.azim.starwars.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import uz.azim.starwars.utils.ViewModelFactory
import javax.inject.Singleton

@Module
interface BaseModule {

    companion object {
        @Provides
        fun provideIoDispatcher(): CoroutineDispatcher {
            return Dispatchers.IO
        }
    }

    @Singleton
    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}
