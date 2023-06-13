package kenali77.projects.hwchallenge.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertiesDAO {

    @Query("SELECT * FROM properties")
    fun getAll(): Flow<List<PropertyModel>>

    @Query("SELECT * FROM properties WHERE id=:id")
    suspend fun getPropertyById(id: Int): PropertyModel

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg properties: PropertyModel)

    @Delete
    suspend fun deleteAll(vararg properties: PropertyModel)

}