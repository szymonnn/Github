package co.netguru.android.github.common.extensions

import java.text.DateFormat
import java.util.*

fun Date.getFormatted(): String = DateFormat.getDateInstance().format(this)