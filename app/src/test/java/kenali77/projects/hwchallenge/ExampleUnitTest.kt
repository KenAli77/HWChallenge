package kenali77.projects.hwchallenge

import kenali77.projects.hwchallenge.data.Resource
import kenali77.projects.hwchallenge.data.remote.ApiService
import kenali77.projects.hwchallenge.data.repo.MainRepositoryImpl
import kenali77.projects.hwchallenge.domain.model.Properties
import kenali77.projects.hwchallenge.ui.home.HomeViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

//    val mockProperties = Properties(
//
//    )
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
   suspend fun `test getProperties() returns Success when API call is successful`() {
        val apiService = mock(ApiService::class.java)
        val responseData = mock(Properties::class.java)
        `when`(apiService.getProperties()).thenReturn(responseData)

        val repo = MainRepositoryImpl(apiService)
        val result = runBlocking { repo.getProperties() }

        assertTrue(result is Resource.Success<Properties>)
        assertEquals(responseData, (result as Resource.Success<Properties>).data)
    }

}