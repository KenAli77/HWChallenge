package kenali77.projects.hwchallenge.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kenali77.projects.hwchallenge.data.Resource
import kenali77.projects.hwchallenge.data.local.database.PropertiesDatabase
import kenali77.projects.hwchallenge.data.local.database.PropertyModel
import kenali77.projects.hwchallenge.data.repo.MainRepositoryImpl
import kenali77.projects.hwchallenge.domain.model.Properties
import kenali77.projects.hwchallenge.domain.model.Property
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MainRepositoryImpl,
    private val db: PropertiesDatabase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set


    private var _searchQuery = MutableStateFlow<String>("")
    val searchQuery = _searchQuery.asStateFlow()

    /**
     * Filtering function for instant search results
     */
    private var _properties = MutableStateFlow(state.properties)
    val properties = searchQuery.combine(_properties) { query, properties ->
        state = state.copy(
            loading = true
        )
        if (query.isBlank()) {
            state = state.copy(loading = false)
            return@combine properties
        } else {

            state = state.copy(loading = false)
            return@combine properties?.filter {
                val combinations = listOf(it.name)
                it.doesMatchSearchQuery(query, combinations)
            }
        }

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _properties.value)

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

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
                            properties = it.properties,
                            error = null,
                            loading = false,
                        )

                        _properties.value = it.properties

                        addPropertiesToDb(it.properties)
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

    /**
    This method takes in the properties fetched from the API and caches them locally
     */
    private fun addPropertiesToDb(properties: List<Property>) {
        viewModelScope.launch {
            val propertiesList = ArrayList<PropertyModel>()
            for (property in properties) {
                val prop: PropertyModel = populatePropertyModelData(property)
                propertiesList.add(prop)
            }
            db.propertiesDao().insertAll(*propertiesList.toTypedArray())
        }
    }

    /**
     * This method generates a PropertyModel object from a Property, in order to
     * be ready for storage in the local DB
     */
    fun populatePropertyModelData(property: Property): PropertyModel {
        return PropertyModel(
            address1 = property.address1,
            address2 = property.address2,
            distance = property.distance,
            facilities = property.facilities,
            freeCancellationAvailable = property.freeCancellationAvailable,
            freeCancellationAvailableUntil = property.freeCancellationAvailableUntil,
            hbid = property.hbid,
            hostelworldRecommends = property.hostelworldRecommends,
            id = property.id,
            imagesGallery = property.imagesGallery,
            isElevate = property.isElevate,
            isFeatured = property.isFeatured,
            isNew = property.isNew,
            isPromoted = property.isPromoted,
            latitude = property.latitude,
            longitude = property.longitude,
            lowestAverageDormPricePerNight = property.lowestAverageDormPricePerNight,
            lowestAveragePrivatePricePerNight = property.lowestAveragePrivatePricePerNight,
            LowestAveragePricePerNight = property.lowestAveragePricePerNight,
            name = property.name,
            overallRating = property.overallRating,
            overview = property.overview,
            position = property.position,
            ratingBreakdown = property.ratingBreakdown,
            starRating = property.starRating,
            type = property.type,
            veryPopular = property.veryPopular,

            )
    }

    data class HomeState(
        val properties: List<Property>? = null,
        val searchQuery: String? = null,
        val error: String? = null,
        val loading: Boolean = false
    )

}