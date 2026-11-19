package it.manumax78.barcodehelper

import kotlin.test.Test
import kotlin.test.assertEquals

class AppNameTest {
    @Test
    fun appNameIsBarcodeHelper() {
        assertEquals("Barcode Helper", appName())
    }
}
