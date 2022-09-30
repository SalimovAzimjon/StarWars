package uz.azim.starwars.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import uz.azim.starwars.data.models.FilmResponse

interface FilmService {

    @GET
    suspend fun getFilm(@Url url: String): Response<FilmResponse>

}
