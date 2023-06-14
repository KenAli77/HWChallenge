package kenali77.projects.hwchallenge.data.repo

import android.util.Log
import kenali77.projects.hwchallenge.data.Resource
import kenali77.projects.hwchallenge.data.remote.ApiService
import kenali77.projects.hwchallenge.domain.model.Properties
import kenali77.projects.hwchallenge.domain.model.Property
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kenali77.projects.hwchallenge.domain.repo.MainRepository as MainRepository

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService) : MainRepository {


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: MainRepositoryImpl? = null

        fun getInstance(apiService: ApiService) =
            instance ?: synchronized(this) {
                instance ?: MainRepositoryImpl(apiService).also { instance = it }
            }
    }



    override suspend fun getProperties(): Resource<Properties> {
        return try {
            val data = apiService.getProperties()
            Resource.Success(data)
        } catch (e: Exception) {
            e.printStackTrace()
            e.message?.let {
                Resource.Error(it)
            } ?: Resource.Error("Failed to get properties")

        }
    }
}