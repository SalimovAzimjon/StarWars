package uz.azim.starwars.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.azim.starwars.BuildConfig
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [ServiceModule::class]
)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(ChuckerInterceptor.Builder(context).build()).build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Named("baseUrl")
    @Provides
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        converter: GsonConverterFactory,
        client: OkHttpClient,
        @Named("baseUrl") url: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(converter)
            .client(client)
            .build()
    }


}

