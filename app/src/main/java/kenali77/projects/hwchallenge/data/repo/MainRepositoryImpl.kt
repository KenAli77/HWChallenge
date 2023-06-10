package kenali77.projects.hwchallenge.data.repo

import android.util.Log
import kenali77.projects.hwchallenge.data.Resource
import kenali77.projects.hwchallenge.data.remote.ApiService
import kenali77.projects.hwchallenge.domain.model.Properties
import javax.inject.Inject
import kenali77.projects.hwchallenge.domain.repo.MainRepository as MainRepository

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService) : MainRepository {

    override suspend fun getProperties(): Resource<Properties> {
        return try {
            val data = apiService.getProperties()
            Log.e("repo",data.location.city.name)
            Resource.Success(data)
        } catch (e: Exception) {
            e.printStackTrace()
            e.message?.let {
                Resource.Error(it)
            } ?: Resource.Error("Failed to get properties")

        }
    }
}