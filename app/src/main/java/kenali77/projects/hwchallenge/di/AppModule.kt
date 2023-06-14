package kenali77.projects.hwchallenge.di

import android.app.Application
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kenali77.projects.hwchallenge.data.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.*
import javax.inject.Singleton

/**
 * This is a dagger hilt module
 * the purpose of this class is to provide single instances throughout the application
 * of the specified classes
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://gist.githubusercontent.com/ollerandreshw/")
            .build()
            .create()
    }


}