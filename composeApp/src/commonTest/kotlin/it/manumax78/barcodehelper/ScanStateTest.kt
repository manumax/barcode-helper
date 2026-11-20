package it.manumax78.barcodehelper

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ScanStateTest {
    private val good = "4006381333931"
    private val other = "5901234123457"

    @Test
    fun noGuessUntilThreeReadingsAgree() {
        val s = ScanState()
        s.onReading(good)
        s.onReading(good)
        assertNull(s.guess)
        s.onReading(good)
        assertEquals(good, s.guess)
    }

    @Test
    fun invalidReadingsDoNotCount() {
        val s = ScanState()
        repeat(5) { s.onReading("4006381333932") }
        assertNull(s.guess)
    }

    @Test
    fun disagreeingCandidatesAreCountedSeparately() {
        val s = ScanState()
        listOf(good, other, good, other).forEach { s.onReading(it) }
        assertNull(s.guess)
        s.onReading(other)
        assertEquals(other, s.guess)
    }

    @Test
    fun dismissResetsAgreementAndStartsNewScan() {
        val s = ScanState()
        repeat(3) { s.onReading(good) }
        s.dismiss()
        assertNull(s.guess)
        repeat(2) { s.onReading(good) }
        assertNull(s.guess)
    }

    @Test
    fun readingsAreIgnoredWhileGuessShown() {
        val s = ScanState()
        repeat(3) { s.onReading(good) }
        repeat(5) { s.onReading(other) }
        assertEquals(good, s.guess)
    }

    private fun readingsEvery(ms: Long, readings: List<String>): suspend ((String) -> Unit) -> Unit = { on ->
        for (r in readings) { delay(ms); on(r) }
    }

    @Test
    fun neverAgreeingReadingsEndInNoGuessAtTimeout() = runTest {
        val s = ScanState()
        val job = launch(start = CoroutineStart.UNDISPATCHED) {
            s.run(readingsEvery(1000, listOf(good, other, good, other) + List(8) { "4006381333932" }))
        }
        advanceTimeBy(SCAN_TIMEOUT_MS - 1)
        assertTrue(!s.noGuess)
        advanceTimeBy(2)
        assertTrue(s.noGuess)
        assertNull(s.guess)
        job.join()
    }

    @Test
    fun guessBeforeTimeoutCancelsTimeout() = runTest {
        val s = ScanState()
        launch(start = CoroutineStart.UNDISPATCHED) { s.run(readingsEvery(500, List(3) { good })) }
        advanceTimeBy(SCAN_TIMEOUT_MS + 1000)
        assertEquals(good, s.guess)
        assertTrue(!s.noGuess)
    }

    @Test
    fun dismissingNoGuessStartsNewScanWithFreshTimeout() = runTest {
        val s = ScanState()
        launch(start = CoroutineStart.UNDISPATCHED) { s.run { delay(Long.MAX_VALUE) } }
        advanceTimeBy(SCAN_TIMEOUT_MS + 1)
        assertTrue(s.noGuess)
        s.dismiss()
        assertTrue(!s.noGuess)
        launch(start = CoroutineStart.UNDISPATCHED) { s.run { delay(Long.MAX_VALUE) } }
        advanceTimeBy(SCAN_TIMEOUT_MS - 1)
        assertTrue(!s.noGuess)
        advanceTimeBy(2)
        assertTrue(s.noGuess)
    }
}
