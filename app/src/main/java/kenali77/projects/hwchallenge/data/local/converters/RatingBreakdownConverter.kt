package kenali77.projects.hwchallenge.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import kenali77.projects.hwchallenge.domain.model.RatingBreakdown

class RatingBreakdownConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromObject(json: String): RatingBreakdown {
        return gson.fromJson(json, RatingBreakdown::class.java)
    }

    @TypeConverter
    fun toObject(ratingBreakdown: RatingBreakdown): String {
        return gson.toJson(ratingBreakdown)
    }
}