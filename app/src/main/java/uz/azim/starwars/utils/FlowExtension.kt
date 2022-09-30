package uz.azim.starwars.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

fun <T> safeFlow(call: suspend () -> T): Flow<Resource<T>> = flow {
        emit(Resource.Success(call.invoke()))
//    try {
//    } catch (exception: Exception) {
//        emit(Resource.Error(exception))
//    }
}
//    .onStart { emit(Resource.Loading()) }
