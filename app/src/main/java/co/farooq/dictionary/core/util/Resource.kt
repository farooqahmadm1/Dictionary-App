package co.farooq.dictionary.core.util

import androidx.room.Database

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data : T? = null, val msg : String? = null){
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(msg: String?,data: T? = null) : Resource<T>(data,msg)
}
