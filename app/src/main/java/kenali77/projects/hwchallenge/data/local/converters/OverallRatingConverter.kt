package kenali77.projects.hwchallenge.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import kenali77.projects.hwchallenge.domain.model.OverallRating

class OverallRatingConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromJson(json: String): OverallRating {
        return gson.fromJson(json, OverallRating::class.java)
    }

    @TypeConverter
    fun toJson(overallRating: OverallRating): String {
        return gson.toJson(overallRating)
    }

}