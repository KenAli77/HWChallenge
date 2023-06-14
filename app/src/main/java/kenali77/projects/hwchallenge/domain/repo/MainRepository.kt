package kenali77.projects.hwchallenge.domain.repo

import kenali77.projects.hwchallenge.data.Resource
import kenali77.projects.hwchallenge.domain.model.Properties

/**
 * This interface defines the methods of the Main Repository
 */

interface MainRepository {

    suspend fun getProperties():Resource<Properties>

}