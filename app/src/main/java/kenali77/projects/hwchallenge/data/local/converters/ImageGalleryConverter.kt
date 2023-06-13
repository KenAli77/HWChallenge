package kenali77.projects.hwchallenge.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kenali77.projects.hwchallenge.domain.model.ImagesGallery

class ImageGalleryConverter {

    @TypeConverter
    fun fromList(value:List<ImagesGallery>):String{
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toList(value:String):List<ImagesGallery>{
        val gson = Gson()
        val type = object : TypeToken<List<ImagesGallery>>() {}.type
        return gson.fromJson(value,type)
    }

}