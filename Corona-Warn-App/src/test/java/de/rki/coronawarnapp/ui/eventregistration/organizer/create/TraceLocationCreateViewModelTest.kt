package de.rki.coronawarnapp.ui.eventregistration.organizer.create

import de.rki.coronawarnapp.R
import de.rki.coronawarnapp.server.protocols.internal.pt.TraceLocationOuterClass
import de.rki.coronawarnapp.ui.eventregistration.organizer.category.adapter.category.TraceLocationCategory
import de.rki.coronawarnapp.ui.eventregistration.organizer.category.adapter.category.TraceLocationUIType
import io.kotest.matchers.shouldBe
import org.joda.time.Duration
import org.joda.time.LocalDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import testhelpers.BaseTest
import testhelpers.extensions.InstantExecutorExtension
import testhelpers.extensions.observeForTesting

@ExtendWith(InstantExecutorExtension::class)
internal class TraceLocationCreateViewModelTest : BaseTest() {

    @ParameterizedTest
    @MethodSource("provideArguments")
    fun `send should not be enabled for empty form`(category: TraceLocationCategory) {
        val viewModel = TraceLocationCreateViewModel(category = category)
        viewModel.uiState.observeForTesting {
            viewModel.uiState.value?.isSendEnable shouldBe false
        }
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    fun `title should be set according to the category item`(category: TraceLocationCategory) {
        val viewModel = TraceLocationCreateViewModel(category = category)
        viewModel.uiState.observeForTesting {
            viewModel.uiState.value?.title shouldBe category.title
        }
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    fun `send should be enabled when all data are entered`(category: TraceLocationCategory) {
        val viewModel = TraceLocationCreateViewModel(category = category)

        viewModel.address = "Address"
        viewModel.description = "Description"
        viewModel.begin = LocalDateTime()
        viewModel.end = LocalDateTime().plusHours(1)
        viewModel.checkInLength = Duration.standardMinutes(1)

        viewModel.uiState.observeForTesting {
            viewModel.uiState.value?.isSendEnable shouldBe true
        }
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    fun `send should not not be enabled when description it too long`(category: TraceLocationCategory) {
        val viewModel = TraceLocationCreateViewModel(category = category)

        viewModel.address = "Address"
        viewModel.description = "A".repeat(101)
        viewModel.begin = LocalDateTime()
        viewModel.end = LocalDateTime().plusHours(1)
        viewModel.checkInLength = Duration.standardMinutes(1)

        viewModel.uiState.observeForTesting {
            viewModel.uiState.value?.isSendEnable shouldBe false
        }
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    fun `send should not not be enabled when address it too long`(category: TraceLocationCategory) {
        val viewModel = TraceLocationCreateViewModel(category = category)

        viewModel.address = "A".repeat(101)
        viewModel.description = "Description"
        viewModel.begin = LocalDateTime()
        viewModel.end = LocalDateTime().plusHours(1)
        viewModel.checkInLength = Duration.standardMinutes(1)

        viewModel.uiState.observeForTesting {
            viewModel.uiState.value?.isSendEnable shouldBe false
        }
    }

    @Test
    fun `begin and end should be visible for EVENT`() {
        val viewModel = TraceLocationCreateViewModel(category = categoryEvent)
        viewModel.uiState.observeForTesting {
            viewModel.uiState.value?.isDateVisible shouldBe true
        }
    }

    @Test
    fun `begin and end should not be visible for LOCATION`() {
        val viewModel = TraceLocationCreateViewModel(category = categoryLocation)
        viewModel.uiState.observeForTesting {
            viewModel.uiState.value?.isDateVisible shouldBe false
        }
    }

    @Test
    fun `send should not be enabled when length of stay is ZERO and category is LOCATION`() {
        val viewModel = TraceLocationCreateViewModel(category = categoryLocation)

        viewModel.address = "Address"
        viewModel.description = "Description"
        viewModel.checkInLength = Duration.ZERO

        viewModel.uiState.observeForTesting {
            viewModel.uiState.value?.isSendEnable shouldBe false
        }
    }

    @Test
    fun `send should be enabled when length of stay is ZERO and category is EVENT`() {
        val viewModel = TraceLocationCreateViewModel(category = categoryEvent)

        viewModel.address = "Address"
        viewModel.description = "Description"
        viewModel.begin = LocalDateTime()
        viewModel.end = LocalDateTime().plusHours(1)
        viewModel.checkInLength = Duration.ZERO

        viewModel.uiState.observeForTesting {
            viewModel.uiState.value?.isSendEnable shouldBe true
        }
    }

    @Test
    fun `send should not be enabled when end is before begin and category is EVENT`() {
        val viewModel = TraceLocationCreateViewModel(category = categoryEvent)

        viewModel.address = "Address"
        viewModel.description = "Description"
        viewModel.begin = LocalDateTime().plusHours(1)
        viewModel.end = LocalDateTime()
        viewModel.checkInLength = Duration.ZERO

        viewModel.uiState.observeForTesting {
            viewModel.uiState.value?.isSendEnable shouldBe false
        }
    }

    companion object {
        private val categoryLocation = TraceLocationCategory(
            TraceLocationOuterClass.TraceLocationType.LOCATION_TYPE_PERMANENT_RETAIL,
            TraceLocationUIType.LOCATION,
            R.string.tracelocation_organizer_category_retail_title,
            R.string.tracelocation_organizer_category_retail_subtitle
        )

        private val categoryEvent = TraceLocationCategory(
            TraceLocationOuterClass.TraceLocationType.LOCATION_TYPE_TEMPORARY_CULTURAL_EVENT,
            TraceLocationUIType.EVENT,
            R.string.tracelocation_organizer_category_cultural_event_title,
            R.string.tracelocation_organizer_category_cultural_event_subtitle
        )

        @Suppress("unused")
        @JvmStatic
        fun provideArguments() = listOf(categoryEvent, categoryLocation)
    }
}
