package com.jaino.common.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.jaino.common.databinding.DialogConfirmBinding

class ConfirmDialog  (
    context : Context,
    private val title : String,
    private val onDoneButtonClick: () -> Unit
): Dialog(context){

    private val binding by lazy { DialogConfirmBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(binding.root)
        initViews()
        resizeDialog()
    }

    private fun initViews(){
        binding.dialogConfirmTextView.text = title

        binding.dialogConfirmPositiveButton.setOnClickListener{
            onDoneButtonClick()
            dismiss()
        }

        binding.dialogConfirmNegativeButton.setOnClickListener{
            dismiss()
        }
    }

    private fun resizeDialog(){
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}