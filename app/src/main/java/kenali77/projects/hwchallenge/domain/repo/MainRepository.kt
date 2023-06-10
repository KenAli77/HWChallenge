package kenali77.projects.hwchallenge.domain.repo

import kenali77.projects.hwchallenge.data.Resource
import kenali77.projects.hwchallenge.domain.model.Properties

interface MainRepository {

    suspend fun getProperties():Resource<Properties>
}