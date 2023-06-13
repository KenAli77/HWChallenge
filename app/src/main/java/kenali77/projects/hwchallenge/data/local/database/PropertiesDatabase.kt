package kenali77.projects.hwchallenge.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kenali77.projects.hwchallenge.data.local.converters.*

@Database(entities = [PropertyModel::class], version = 1)
@TypeConverters(
    PropertiesConverter::class,
    ImageGalleryConverter::class,
    FacilitiesConverter::class,
    LowestAvgPricePerNightConverter::class,
    OverallRatingConverter::class,
    RatingBreakdownConverter::class,
    DistanceConverter::class
)

abstract class PropertiesDatabase : RoomDatabase() {

    abstract fun propertiesDao(): PropertiesDAO

    companion object {
        @Volatile
        private var INSTANCE: PropertiesDatabase? = null

        fun getInstance(context: Context): PropertiesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PropertiesDatabase::class.java,
                        "properties"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance

                }
                return instance

            }

        }
    }
}