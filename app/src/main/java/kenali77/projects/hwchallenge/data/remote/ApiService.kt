package kenali77.projects.hwchallenge.data.remote

import kenali77.projects.hwchallenge.domain.model.Properties
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val API_URL = "e02c83a2c973c625bbc250e1d93a2040/raw/55b40d1b4e96fd8cde73aebb8d229a45dff28f2d/properties.json"
    }

    @GET(API_URL)
    suspend fun getProperties(): Properties

}