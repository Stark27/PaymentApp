package com.example.luismunoz.paymentapp.util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class Util {

    companion object {

        fun currencyDecimalFormat(amount: Double): String {
            if (amount >= ZERO_AMOUNT) {
                val symbol = DecimalFormatSymbols()
                symbol.decimalSeparator = ','
                symbol.groupingSeparator = '.'

                val decimalFormat = DecimalFormat("#,###.##", symbol)
                decimalFormat.roundingMode = RoundingMode.DOWN
                return CURRENCY_SIGN + decimalFormat.format(amount)
            } else {
                return CURRENCY_SIGN + NUMBER_FORMAT_DECIMAL.format(amount * -1L)
            }
        }

        fun currencyFormat(amount: Int): String {
            if (amount >= ZERO_AMOUNT) {
                return CURRENCY_SIGN + NUMBER_FORMAT.format(amount).replace(',', '.')
            } else {
                return CURRENCY_SIGN + NUMBER_FORMAT.format(amount * -1L).replace(',', '.')
            }
        }

        fun cleanAmount(amount: String): String {
            if (amount.isNotEmpty()) {
                return amount.replace(CURRENCY_SIGN, "").replace(".", "").replace(" ", "").trim()
            }
            return ZERO_STRING
        }
    }
}