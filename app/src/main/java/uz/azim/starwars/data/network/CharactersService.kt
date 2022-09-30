package uz.azim.starwars.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import uz.azim.starwars.data.models.CharacterResponse
import uz.azim.starwars.data.models.PagingResponse
import uz.azim.starwars.data.models.SpeciesResponse

interface CharactersService {

    @GET("people")
    suspend fun getCharacters(@Query("page") page: Int): Response<PagingResponse<CharacterResponse>>

    @GET
    suspend fun getCharacters(@Url url: String): Response<PagingResponse<CharacterResponse>>

    @GET
    suspend fun getSpecies(@Url url: String): Response<SpeciesResponse>

}
