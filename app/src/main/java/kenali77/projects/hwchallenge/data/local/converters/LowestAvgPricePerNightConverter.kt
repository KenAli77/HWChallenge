package kenali77.projects.hwchallenge.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import kenali77.projects.hwchallenge.domain.model.LowestAverageDormPricePerNight
import kenali77.projects.hwchallenge.domain.model.LowestAveragePricePerNight
import kenali77.projects.hwchallenge.domain.model.LowestAveragePrivatePricePerNight

class LowestAvgPricePerNightConverter {
    private val gson = Gson()


    @TypeConverter
    fun fromPrice(json: String?): LowestAveragePricePerNight? {
        return gson.fromJson(json, LowestAveragePricePerNight::class.java)
    }

    @TypeConverter
    fun toPrice(lowestAveragePricePerNight: LowestAveragePricePerNight?): String {
        return gson.toJson(lowestAveragePricePerNight)
    }

    @TypeConverter
    fun fromDormPrice(json: String?): LowestAverageDormPricePerNight? {
        return gson.fromJson(json, LowestAverageDormPricePerNight::class.java)
    }

    @TypeConverter
    fun toDormPrice(lowestAveragePricePerNight: LowestAverageDormPricePerNight?): String {
        return gson.toJson(lowestAveragePricePerNight)
    }

    @TypeConverter
    fun fromPrivatePrice(json: String?): LowestAveragePrivatePricePerNight? {
        return gson.fromJson(json, LowestAveragePrivatePricePerNight::class.java)
    }

    @TypeConverter
    fun toPrivatePrice(lowestAveragePricePerNight: LowestAveragePrivatePricePerNight?): String {
        return gson.toJson(lowestAveragePricePerNight)
    }
}