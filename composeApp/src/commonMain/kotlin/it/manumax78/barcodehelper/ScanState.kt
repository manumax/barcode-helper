package it.manumax78.barcodehelper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

const val REQUIRED_AGREEMENT = 3

/** Holds one Scan: counts Agreement per Candidate until a Guess emerges. */
class ScanState {
    private val counts = mutableMapOf<String, Int>()

    var guess: String? by mutableStateOf(null)
        private set

    fun onReading(printedNumber: String) {
        if (guess != null || !Ean13.isValid(printedNumber)) return
        val n = (counts[printedNumber] ?: 0) + 1
        counts[printedNumber] = n
        if (n >= REQUIRED_AGREEMENT) guess = printedNumber
    }

    fun dismiss() {
        guess = null
        counts.clear()
    }
}
