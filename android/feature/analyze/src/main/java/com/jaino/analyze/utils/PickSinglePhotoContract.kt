package com.jaino.analyze.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.ext.SdkExtensions.getExtensionVersion
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract

class PickSinglePhotoContract : ActivityResultContract<Void?, Uri?>() {

    companion object{
        const val MIME_TYPE_IMAGE = "image/*"
    }

    override fun createIntent(context: Context, input: Void?): Intent {
        return Intent(if(PhotoPickerAvailabilityChecker.isPhotoPickerAvailable()){
            Intent(MediaStore.ACTION_PICK_IMAGES)
        }else{
            Intent(Intent.ACTION_OPEN_DOCUMENT)
        }).apply {
            type = MIME_TYPE_IMAGE
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return intent.takeIf { resultCode == Activity.RESULT_OK }?.data
    }
}

object PhotoPickerAvailabilityChecker {

    private const val ANDROID_R_REQUIRED_EXTENSION_VERSION = 2

    fun isPhotoPickerAvailable(): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> true
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                getExtensionVersion(Build.VERSION_CODES.R) >= ANDROID_R_REQUIRED_EXTENSION_VERSION
            }
            else -> false
        }
    }
}
