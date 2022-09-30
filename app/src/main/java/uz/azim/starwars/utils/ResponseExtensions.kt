package uz.azim.starwars.utils

import android.util.Log
import retrofit2.Response
import java.net.HttpURLConnection

fun <T> Response<T>.handleResponse(): T {
    val body = body()
    return if (isSuccessful && body != null) {
        body
    } else {
        Log.d("TAGx", "${message()}")
        throw when (code()) {
            HttpURLConnection.HTTP_INTERNAL_ERROR -> ServerNotRespondingException(message())
            else -> UnkownException(message())
        }
    }
}
