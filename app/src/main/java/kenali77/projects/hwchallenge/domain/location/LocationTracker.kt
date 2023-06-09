package kenali77.projects.hwchallenge.domain.location

import android.location.Location

interface LocationTracker {

    suspend fun getCurrentLocation(): Location?


}