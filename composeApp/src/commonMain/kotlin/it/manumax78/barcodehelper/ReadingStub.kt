package it.manumax78.barcodehelper

import kotlinx.coroutines.delay

/** Stand-in for the camera: emits a sequence of Readings. Later tickets add other sequences. */
object ReadingStub {
    val legible: List<String> = List(3) { "4006381333931" }

    suspend fun emit(readings: List<String> = legible, intervalMs: Long = 500, onReading: (String) -> Unit) {
        for (r in readings) {
            delay(intervalMs)
            onReading(r)
        }
    }
}
