package de.rki.coronawarnapp.eventregistration.storage.retention

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.Reusable
import org.joda.time.DateTimeConstants
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@Reusable
class TraceLocationCleanUpScheduler @Inject constructor(
    private val workManager: WorkManager
) {

    private val periodicWorkRequest = PeriodicWorkRequestBuilder<TraceLocationCleanUpPeriodicWorker>(
        DateTimeConstants.HOURS_PER_DAY.toLong(),
        TimeUnit.HOURS
    ).build()

    fun scheduleDaily() {
        workManager.enqueueUniquePeriodicWork(
            PERIODIC_WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicWorkRequest
        )
    }

    companion object {
        const val PERIODIC_WORK_NAME = "TraceLocationCleanUpPeriodicWork"
    }
}
