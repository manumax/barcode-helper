package it.manumax78.barcodehelper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.withTimeoutOrNull

const val REQUIRED_AGREEMENT = 3
const val SCAN_TIMEOUT_MS = 10_000L

/** Holds one Scan: counts Agreement per Candidate until a Guess emerges. */
class ScanState {
    private val counts = mutableMapOf<String, Int>()

    var guess: String? by mutableStateOf(null)
        private set

    var noGuess: Boolean by mutableStateOf(false)
        private set

    /** Runs the Scan: feeds Readings to [onReading]; ends in No Guess if no Guess within [SCAN_TIMEOUT_MS]. */
    suspend fun run(readings: suspend ((String) -> Unit) -> Unit) {
        val finished = withTimeoutOrNull(SCAN_TIMEOUT_MS) {
            readings(::onReading)
            // Readings exhausted: wait for the timeout unless a Guess already ended the Scan.
            if (guess == null) kotlinx.coroutines.awaitCancellation()
        }
        if (finished == null && guess == null) noGuess = true
    }

    fun onReading(printedNumber: String) {
        if (guess != null || noGuess || !Ean13.isValid(printedNumber)) return
        val n = (counts[printedNumber] ?: 0) + 1
        counts[printedNumber] = n
        if (n >= REQUIRED_AGREEMENT) guess = printedNumber
    }

    fun dismiss() {
        guess = null
        noGuess = false
        counts.clear()
    }
}
