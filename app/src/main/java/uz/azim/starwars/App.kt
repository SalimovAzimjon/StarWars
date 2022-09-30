package uz.azim.starwars

import android.app.Application
import uz.azim.starwars.di.AppComponent

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this)
    }

    fun provideAppComponent() = appComponent

}
