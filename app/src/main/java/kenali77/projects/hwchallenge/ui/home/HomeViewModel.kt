package kenali77.projects.hwchallenge.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kenali77.projects.hwchallenge.data.Resource
import kenali77.projects.hwchallenge.data.repo.MainRepositoryImpl
import kenali77.projects.hwchallenge.domain.model.Properties
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: MainRepositoryImpl) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getProperties()
    }

    private fun getProperties() {
        state = state.copy(
            properties = null,
            error = null,
            loading = true,
        )

        viewModelScope.launch {
            val result = repo.getProperties()

            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        state = state.copy(
                            properties = it,
                            error = null,
                            loading = false,
                        )
                    }
                }
                is Resource.Error -> {
                    state = state.copy(
                        properties = null,
                        error = result.message,
                        loading = false,
                    )
                }
            }
        }
    }

    data class HomeState(
        val properties: Properties? = null,
        val error: String? = null,
        val loading: Boolean? = false
    )

}