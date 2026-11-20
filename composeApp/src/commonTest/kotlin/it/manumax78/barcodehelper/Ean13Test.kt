package it.manumax78.barcodehelper

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Ean13Test {
    @Test
    fun knownGoodNumbersAreValid() {
        listOf("4006381333931", "5901234123457", "9780201379624", "0000000000000")
            .forEach { assertTrue(Ean13.isValid(it), it) }
    }

    @Test
    fun wrongCheckDigitIsInvalid() {
        assertFalse(Ean13.isValid("4006381333932"))
        assertFalse(Ean13.isValid("5901234123458"))
    }

    @Test
    fun wrongShapeIsInvalid() {
        assertFalse(Ean13.isValid("400638133393"))
        assertFalse(Ean13.isValid("40063813339311"))
        assertFalse(Ean13.isValid("40063813339a1"))
        assertFalse(Ean13.isValid(""))
    }

    @Test
    fun barPatternMatchesStandardEncoding() {
        val expected = "0".repeat(9) +
            "101" +
            "0001101" + "0100111" + "0101111" + "0111101" + "0001001" + "0110011" +
            "01010" +
            "1000010" + "1000010" + "1000010" + "1110100" + "1000010" + "1100110" +
            "101" +
            "0".repeat(7)
        val modules = Ean13.modules("4006381333931")
        assertEquals(expected, modules.joinToString("") { if (it) "1" else "0" })
        assertEquals(111, modules.size)
    }
}
