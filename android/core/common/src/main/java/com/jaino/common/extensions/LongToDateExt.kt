package com.jaino.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateTime() : String = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREAN).format(this)