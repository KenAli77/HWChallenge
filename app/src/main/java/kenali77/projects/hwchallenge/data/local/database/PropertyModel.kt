package kenali77.projects.hwchallenge.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kenali77.projects.hwchallenge.data.local.converters.*
import kenali77.projects.hwchallenge.domain.model.*

@Entity(tableName = "properties")
data class PropertyModel(
    var address1: String,
    val address2: String,
    @TypeConverters(DistanceConverter::class)
    val distance: Distance,
    @TypeConverters(FacilitiesConverter::class)
    val facilities: List<Facility>,
    val freeCancellationAvailable: Boolean,
    val freeCancellationAvailableUntil: String,
    val hbid: Int,
    val hostelworldRecommends: Boolean,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @TypeConverters(ImageGalleryConverter::class)
    val imagesGallery: List<ImagesGallery>,
    val isElevate: Boolean,
    val isFeatured: Boolean,
    val isNew: Boolean,
    val isPromoted: Boolean,
    val latitude: Double,
    val longitude: Double,
    @TypeConverters(LowestAvgPricePerNightConverter::class)
    val lowestAverageDormPricePerNight: LowestAverageDormPricePerNight?,
    @TypeConverters(LowestAvgPricePerNightConverter::class)
    val lowestAveragePrivatePricePerNight: LowestAveragePrivatePricePerNight?,
    @TypeConverters(LowestAvgPricePerNightConverter::class)
    val LowestAveragePricePerNight:LowestAveragePricePerNight?,
    val name: String,
    @TypeConverters(OverallRatingConverter::class)
    val overallRating: OverallRating,
    val overview: String,
    val position: Int,
    @TypeConverters(RatingBreakdownConverter::class)
    val ratingBreakdown: RatingBreakdown,
    val starRating: Int,
    val type: String,
    val veryPopular: Boolean
) {



}