package uz.azim.starwars.data.models


import com.google.gson.annotations.SerializedName

data class PagingResponse<T>(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<T>
)
