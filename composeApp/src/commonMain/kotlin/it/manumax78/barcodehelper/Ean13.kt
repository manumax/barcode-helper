package it.manumax78.barcodehelper

object Ean13 {
    private val L = listOf("0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011")
    private val R = L.map { code -> code.map { if (it == '1') '0' else '1' }.joinToString("") }
    private val G = R.map { it.reversed() }
    private val PARITY = listOf("LLLLLL", "LLGLGG", "LLGGLG", "LLGGGL", "LGLLGG", "LGGLLG", "LGGGLL", "LGLGLG", "LGLGGL", "LGGLGL")

    const val QUIET_LEFT = 9
    const val QUIET_RIGHT = 7

    fun isValid(number: String): Boolean {
        if (number.length != 13 || !number.all { it in '0'..'9' }) return false
        val sum = number.take(12).withIndex().sumOf { (i, c) -> (c - '0') * if (i % 2 == 0) 1 else 3 }
        return (10 - sum % 10) % 10 == number[12] - '0'
    }

    /** Bar pattern (true = dark) including quiet zones; requires a Valid Number. */
    fun modules(number: String): List<Boolean> {
        require(isValid(number)) { "Not a valid EAN-13: $number" }
        val parity = PARITY[number[0] - '0']
        val bits = StringBuilder("0".repeat(QUIET_LEFT)).append("101")
        for (i in 0 until 6) {
            val d = number[i + 1] - '0'
            bits.append(if (parity[i] == 'L') L[d] else G[d])
        }
        bits.append("01010")
        for (i in 7 until 13) bits.append(R[number[i] - '0'])
        bits.append("101").append("0".repeat(QUIET_RIGHT))
        return bits.map { it == '1' }
    }
}
