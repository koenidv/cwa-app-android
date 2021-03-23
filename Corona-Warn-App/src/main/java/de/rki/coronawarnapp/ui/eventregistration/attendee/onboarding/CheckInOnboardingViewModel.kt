package de.rki.coronawarnapp.ui.eventregistration.attendee.onboarding

import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import de.rki.coronawarnapp.util.ui.SingleLiveEvent
import de.rki.coronawarnapp.util.viewmodel.CWAViewModel
import de.rki.coronawarnapp.util.viewmodel.SimpleCWAViewModelFactory

class CheckInOnboardingViewModel @AssistedInject constructor() : CWAViewModel() {
    val events = SingleLiveEvent<CheckInOnboardingNavigation>()

    fun onAcknowledged() {
        events.value = CheckInOnboardingNavigation.AcknowledgedNavigation
    }

    fun onPrivacy() {
        events.value = CheckInOnboardingNavigation.DataProtectionNavigation
    }

    @AssistedFactory
    interface Factory : SimpleCWAViewModelFactory<CheckInOnboardingViewModel>
}
