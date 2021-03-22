package de.rki.coronawarnapp.eventregistration.checkins.riskcalculation

import de.rki.coronawarnapp.eventregistration.checkins.CheckIn
import de.rki.coronawarnapp.eventregistration.checkins.CheckInRepository
import de.rki.coronawarnapp.eventregistration.checkins.download.TraceTimeIntervalWarningPackage
import de.rki.coronawarnapp.eventregistration.checkins.download.TraceTimeIntervalWarningRepository
import de.rki.coronawarnapp.eventregistration.checkins.split.splitByMidnightUTC
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class TraceLocationCheckInMatcher @Inject constructor(
    private val checkInsRepository: CheckInRepository,
    private val traceTimeIntervalWarningRepository: TraceTimeIntervalWarningRepository
) {
    suspend fun execute(): List<CheckInOverlap> {
        val checkIns = checkInsRepository.allCheckIns.firstOrNull() ?: return emptyList()

        // TODO only new packages
        val warningPackages = traceTimeIntervalWarningRepository.allWarningPackages.firstOrNull() ?: return emptyList()

        // TODO store matches in db
        return warningPackages
            .flatMap { warningPackage ->
                findMatches(checkIns, warningPackage)
            }
    }
}

private suspend fun findMatches(
    checkIns: List<CheckIn>,
    warningPackage: TraceTimeIntervalWarningPackage
): List<CheckInOverlap> {

    val relevantWarnings =
        filterRelevantWarnings(
            checkIns,
            warningPackage
        )

    if (relevantWarnings.isEmpty()) return emptyList()

    return relevantWarnings
        .flatMap { warning ->
            checkIns
                .flatMap { it.splitByMidnightUTC() }
                .mapNotNull { it.calculateOverlap(warning) }
        }
}
