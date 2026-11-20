package it.manumax78.barcodehelper

import kotlinx.coroutines.delay

/** Stand-in for the camera: emits a sequence of Readings. Later tickets add other sequences. */
object ReadingStub {
    val legible: List<String> = List(3) { "4006381333931" }

    /** Readings that keep disagreeing, so a Scan can never reach Agreement (each valid Candidate appears < 3 times). */
    val neverAgreeing: List<String> = listOf("4006381333931", "5901234123457", "4006381333931", "5901234123457") +
        List(26) { "4006381333932" } // invalid check digit: never counts

    suspend fun emit(readings: List<String> = legible, intervalMs: Long = 500, onReading: (String) -> Unit) {
        for (r in readings) {
            delay(intervalMs)
            onReading(r)
        }
    }
}
