package com.jaino.analyze.utils

import android.content.ContentValues
import android.os.Build
import android.provider.MediaStore
import java.text.SimpleDateFormat
import java.util.*

fun ContentValues.getCurrentFileName() : ContentValues {
    val name = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        .format(System.currentTimeMillis())

    return ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/HealthC")
        }
    }
}