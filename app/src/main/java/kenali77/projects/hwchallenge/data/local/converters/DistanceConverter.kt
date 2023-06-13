package kenali77.projects.hwchallenge.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import kenali77.projects.hwchallenge.domain.model.Distance

class DistanceConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromJson(json: String): Distance {
        return gson.fromJson(json, Distance::class.java)
    }

    @TypeConverter
    fun toJson(distance: Distance): String {
        return gson.toJson(distance)
    }

}
