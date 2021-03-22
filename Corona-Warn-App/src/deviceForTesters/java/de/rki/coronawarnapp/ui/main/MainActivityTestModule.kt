package de.rki.coronawarnapp.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.rki.coronawarnapp.miscinfo.MiscInfoFragment
import de.rki.coronawarnapp.miscinfo.MiscInfoFragmentModule
import de.rki.coronawarnapp.test.appconfig.ui.AppConfigTestFragment
import de.rki.coronawarnapp.test.appconfig.ui.AppConfigTestFragmentModule
import de.rki.coronawarnapp.test.contactdiary.ui.ContactDiaryTestFragment
import de.rki.coronawarnapp.test.contactdiary.ui.ContactDiaryTestFragmentModule
import de.rki.coronawarnapp.test.datadonation.ui.DataDonationTestFragment
import de.rki.coronawarnapp.test.datadonation.ui.DataDonationTestFragmentModule
import de.rki.coronawarnapp.test.debugoptions.ui.DebugOptionsFragment
import de.rki.coronawarnapp.test.debugoptions.ui.DebugOptionsFragmentModule
import de.rki.coronawarnapp.test.deltaonboarding.ui.DeltaOnboardingFragmentModule
import de.rki.coronawarnapp.test.deltaonboarding.ui.DeltaonboardingFragment
import de.rki.coronawarnapp.test.eventregistration.ui.EventRegistrationTestFragment
import de.rki.coronawarnapp.test.eventregistration.ui.EventRegistrationTestFragmentModule
import de.rki.coronawarnapp.test.eventregistration.ui.createevent.CreateEventTestFragment
import de.rki.coronawarnapp.test.eventregistration.ui.createevent.CreateEventTestFragmentModule
import de.rki.coronawarnapp.test.eventregistration.ui.qrcode.QrCodeCreationTestFragment
import de.rki.coronawarnapp.test.eventregistration.ui.qrcode.QrCodeCreationTestFragmentModule
import de.rki.coronawarnapp.test.eventregistration.ui.showevents.ShowStoredEventsTestFragment
import de.rki.coronawarnapp.test.eventregistration.ui.showevents.ShowStoredEventsTestFragmentModule
import de.rki.coronawarnapp.test.keydownload.ui.KeyDownloadTestFragment
import de.rki.coronawarnapp.test.keydownload.ui.KeyDownloadTestFragmentModule
import de.rki.coronawarnapp.test.menu.ui.TestMenuFragment
import de.rki.coronawarnapp.test.menu.ui.TestMenuFragmentModule
import de.rki.coronawarnapp.test.playground.ui.PlaygroundFragment
import de.rki.coronawarnapp.test.playground.ui.PlaygroundModule
import de.rki.coronawarnapp.test.risklevel.ui.TestRiskLevelCalculationFragment
import de.rki.coronawarnapp.test.risklevel.ui.TestRiskLevelCalculationFragmentModule
import de.rki.coronawarnapp.test.submission.ui.SubmissionTestFragment
import de.rki.coronawarnapp.test.submission.ui.SubmissionTestFragmentModule
import de.rki.coronawarnapp.test.tasks.ui.TestTaskControllerFragment
import de.rki.coronawarnapp.test.tasks.ui.TestTaskControllerFragmentModule
import de.rki.coronawarnapp.ui.eventregistration.organizer.details.QrCodeDetailFragment
import de.rki.coronawarnapp.ui.eventregistration.organizer.details.QrCodeDetailModule

@Module
abstract class MainActivityTestModule {

    @ContributesAndroidInjector(modules = [TestMenuFragmentModule::class])
    abstract fun testMenuFragment(): TestMenuFragment

    @ContributesAndroidInjector(modules = [TestRiskLevelCalculationFragmentModule::class])
    abstract fun testRiskLevelCalculationFragment(): TestRiskLevelCalculationFragment

    @ContributesAndroidInjector(modules = [MiscInfoFragmentModule::class])
    abstract fun miscInfoFragment(): MiscInfoFragment

    @ContributesAndroidInjector(modules = [TestTaskControllerFragmentModule::class])
    abstract fun testTaskControllerFragment(): TestTaskControllerFragment

    @ContributesAndroidInjector(modules = [AppConfigTestFragmentModule::class])
    abstract fun appConfigTestFragment(): AppConfigTestFragment

    @ContributesAndroidInjector(modules = [DebugOptionsFragmentModule::class])
    abstract fun debugOptions(): DebugOptionsFragment

    @ContributesAndroidInjector(modules = [KeyDownloadTestFragmentModule::class])
    abstract fun keyDownload(): KeyDownloadTestFragment

    @ContributesAndroidInjector(modules = [SubmissionTestFragmentModule::class])
    abstract fun submissionTest(): SubmissionTestFragment

    @ContributesAndroidInjector(modules = [ContactDiaryTestFragmentModule::class])
    abstract fun contactDiaryTest(): ContactDiaryTestFragment

    @ContributesAndroidInjector(modules = [PlaygroundModule::class])
    abstract fun playground(): PlaygroundFragment

    @ContributesAndroidInjector(modules = [DataDonationTestFragmentModule::class])
    abstract fun dataDonation(): DataDonationTestFragment

    @ContributesAndroidInjector(modules = [DeltaOnboardingFragmentModule::class])
    abstract fun deltaOnboarding(): DeltaonboardingFragment

    @ContributesAndroidInjector(modules = [EventRegistrationTestFragmentModule::class])
    abstract fun eventRegistration(): EventRegistrationTestFragment

    @ContributesAndroidInjector(modules = [QrCodeCreationTestFragmentModule::class])
    abstract fun qrCodeCreation(): QrCodeCreationTestFragment

    @ContributesAndroidInjector(modules = [CreateEventTestFragmentModule::class])
    abstract fun createEvent(): CreateEventTestFragment

    @ContributesAndroidInjector(modules = [ShowStoredEventsTestFragmentModule::class])
    abstract fun showStoredEvents(): ShowStoredEventsTestFragment

    @ContributesAndroidInjector(modules = [QrCodeDetailModule::class])
    abstract fun showEventDetail(): QrCodeDetailFragment
}
