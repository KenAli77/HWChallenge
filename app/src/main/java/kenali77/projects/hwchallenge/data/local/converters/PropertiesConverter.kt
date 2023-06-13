package kenali77.projects.hwchallenge.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kenali77.projects.hwchallenge.domain.model.FacilityX
import kenali77.projects.hwchallenge.domain.model.Properties
import kenali77.projects.hwchallenge.domain.model.Property


class PropertiesConverter {

    @TypeConverter
    fun fromList(value:List<Properties>):String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toProperties(value:String):List<Properties> {
        val gson = Gson()
        val type = object : TypeToken<List<Property>>() {}.type
        return gson.fromJson(value,type)

    }


}