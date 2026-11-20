package it.manumax78.barcodehelper

import kotlin.test.Test
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
}
