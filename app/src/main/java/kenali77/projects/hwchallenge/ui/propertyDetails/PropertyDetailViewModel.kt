package kenali77.projects.hwchallenge.ui.propertyDetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kenali77.projects.hwchallenge.data.Resource
import kenali77.projects.hwchallenge.data.repo.MainRepositoryImpl
import kenali77.projects.hwchallenge.domain.model.Property
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    private val repo: MainRepositoryImpl, savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var propertyId: Int? = savedStateHandle["noteId"]

    var state by mutableStateOf(PropertyState())
        private set

    init {

        viewModelScope.launch {
            propertyId?.let {
                state = state.copy(
                    loading = true,
                    data = null,
                    error = null,
                )
               val result =  repo.getPropertyById(it)
                when(result){
                    is Resource.Error -> {
                        state = state.copy(
                            null,
                            error = result.message,
                            loading = false
                        )
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            result.data,
                            error = null,
                            loading = false
                        )
                    }
                }

            }
        }

    }

    data class PropertyState(
        val data: Property? = null,
        val error: String? = null,
        val loading: Boolean? = false
    )
}