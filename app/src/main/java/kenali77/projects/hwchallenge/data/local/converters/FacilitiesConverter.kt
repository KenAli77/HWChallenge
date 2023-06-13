package kenali77.projects.hwchallenge.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kenali77.projects.hwchallenge.domain.model.Facility
import kenali77.projects.hwchallenge.domain.model.FacilityX
import kenali77.projects.hwchallenge.domain.model.ImagesGallery
import kenali77.projects.hwchallenge.domain.model.Properties

class FacilitiesConverter {


    @TypeConverter
    fun fromList(value:List<Facility>):String {
        val gson = Gson()
        return gson.toJson(value)
    }


    @TypeConverter
    fun fromListX(value:List<FacilityX>):String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toList(value:String):List<Facility>{
        val gson = Gson()
        val type = object : TypeToken<List<Facility>>() {}.type
        return gson.fromJson(value,type)
    }


    @TypeConverter
    fun toListX(value:String):List<FacilityX> {
        val gson = Gson()
        val type = object : TypeToken<List<FacilityX>>() {}.type
        return gson.fromJson(value,type)
    }


}