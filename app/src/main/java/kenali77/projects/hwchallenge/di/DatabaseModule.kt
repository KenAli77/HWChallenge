package kenali77.projects.hwchallenge.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kenali77.projects.hwchallenge.data.local.database.PropertiesDAO
import kenali77.projects.hwchallenge.data.local.database.PropertiesDatabase
import javax.inject.Singleton

/**
 * This is a dagger hilt module providing instances of the property database and dao
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providePropertiesDao(db: PropertiesDatabase): PropertiesDAO {
        return db.propertiesDao()
    }

    @Provides
    @Singleton
    fun providePropertiesDatabase(@ApplicationContext context: Context): PropertiesDatabase {
        return PropertiesDatabase.getInstance(context)
    }

}
