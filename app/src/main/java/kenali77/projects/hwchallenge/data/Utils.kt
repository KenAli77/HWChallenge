package kenali77.projects.hwchallenge.data

import android.location.Location
import kotlinx.coroutines.flow.Flow

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}

