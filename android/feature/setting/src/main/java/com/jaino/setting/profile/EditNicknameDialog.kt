package com.jaino.setting.profile

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.jaino.setting.databinding.DialogEditNicknameBinding
import usecase.validate.ValidateNickname

class EditNicknameDialog (
    context : Context,
    private val onDoneButtonClick: (String) -> Unit
): Dialog(context){

    private val binding by lazy { DialogEditNicknameBinding.inflate(layoutInflater) }
    private val validateUseCase by lazy { ValidateNickname() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(binding.root)
        initViews()
        resizeDialog()
    }

    private fun initViews(){
        binding.dialogEditDoneButton.setOnClickListener {
            if(validateName()){
                onDoneButtonClick(binding.dialogEditNameTextView.text.toString())
                dismiss()
            }
        }
    }

    private fun validateName(): Boolean{
        val name = binding.dialogEditNameTextView.text.toString()
        val validateResult = validateUseCase(name)
        if(!validateResult.successful){
            binding.dialogEditNameLayout.error = validateResult.errorMessage
            return false
        }else{
            return true
        }
    }

    private fun resizeDialog(){
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}