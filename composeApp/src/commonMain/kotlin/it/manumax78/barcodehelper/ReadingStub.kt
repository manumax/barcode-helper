package it.manumax78.barcodehelper

import kotlinx.coroutines.delay

/** Stand-in for the camera: emits a sequence of Readings. Later tickets add other sequences. */
object ReadingStub {
    val legible: List<String> = List(3) { "4006381333931" }

    /** Readings that keep disagreeing, so a Scan can never reach Agreement. */
    val neverAgreeing: List<String> = List(30) { if (it % 2 == 0) "4006381333931" else "5901234123457" }

    suspend fun emit(readings: List<String> = legible, intervalMs: Long = 500, onReading: (String) -> Unit) {
        for (r in readings) {
            delay(intervalMs)
            onReading(r)
        }
    }
}
