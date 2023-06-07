package com.jaino.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract

class PickPhotoContract : ActivityResultContract<Void?, Uri?>() {

    companion object{
        const val MIME_TYPE_IMAGE = "image/*"
    }

    override fun createIntent(context: Context, input: Void?): Intent {
        return Intent(if(PhotoPickerChecker.isPhotoPickerAvailable()){
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