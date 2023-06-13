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
import kenali77.projects.hwchallenge.data.local.database.PropertiesDatabase
import kenali77.projects.hwchallenge.data.local.database.PropertyModel
import kenali77.projects.hwchallenge.data.repo.MainRepositoryImpl
import kenali77.projects.hwchallenge.domain.model.Property
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    private val repo: MainRepositoryImpl, savedStateHandle: SavedStateHandle,
    private val db: PropertiesDatabase
) : ViewModel() {

    private var propertyId: Int? = savedStateHandle["propertyId"]

    var state by mutableStateOf(PropertyState())
        private set

    init {

        viewModelScope.launch {
            propertyId?.let {
                if (it != -1)
                Log.e("id in vm", it.toString())
                state = state.copy(
                    loading = true,
                    data = null,
                    error = null,
                )
                db.propertiesDao()
                val result = db.propertiesDao().getPropertyById(it)
                state = state.copy(
                    data = result,
                    loading = false,
                    error = null
                )


            }
        }

    }

    data class PropertyState(
        val data: PropertyModel? = null,
        val error: String? = null,
        val loading: Boolean? = false
    )
}