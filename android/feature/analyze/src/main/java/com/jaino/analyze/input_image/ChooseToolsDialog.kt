package com.jaino.analyze.input_image

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jaino.analyze.databinding.DialogChooseToolsBinding

class ChooseToolsDialog(
    context : Context,
    private val onCameraClick : () -> Unit,
    private val onAlbumClick : () -> Unit,
    ): BottomSheetDialog(context) {
    private val binding by lazy { DialogChooseToolsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(binding.root)
        initViews()
    }

    private fun initViews(){
        binding.albumPictureButton.setOnClickListener{
            onAlbumClick()
            dismiss()
        }
        binding.cameraPictureButton.setOnClickListener{
            onCameraClick()
            dismiss()
        }
    }
}
