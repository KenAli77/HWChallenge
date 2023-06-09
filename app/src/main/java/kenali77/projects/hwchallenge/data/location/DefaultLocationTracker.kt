package kenali77.projects.hwchallenge.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import kenali77.projects.hwchallenge.domain.location.LocationTracker
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val application: Application,
    private val locationManager: LocationManager,
    private val geocoder: Geocoder

) : LocationTracker {

    companion object {
       const val TAG = "DefaultLocationTracker"
    }

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    private var location = MutableStateFlow<Location?>(null)

    override suspend fun getCurrentLocation(): Location? {

        val hasPermissionCoarseLocation = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasPermissionFineLocation = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


        if (!hasPermissionCoarseLocation || !hasPermissionFineLocation || !isGpsEnabled) {
            return null
        }

        return suspendCancellableCoroutine { cont ->

            fusedLocationProviderClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        scope.launch {
                            location.emit(result)
                        }
                        Log.e(TAG, location.value.toString())

                        cont.resume(result)
                    } else {
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }

                addOnSuccessListener {
                    cont.resume(result)

                }

                addOnFailureListener {
                    cont.resume(null)
                    Log.e("Location", it.message.toString())
                }
                addOnCanceledListener {
                    cont.cancel()
                    Log.e("Location", "CANCELLED")
                }
            }

        }
    }



}