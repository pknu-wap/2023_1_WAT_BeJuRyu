package com.jaino.common.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.jaino.common.databinding.DialogErrorBinding
import java.net.ConnectException
import java.net.HttpRetryException
import java.net.SocketTimeoutException

class ErrorDialog(
    context: Context,
    private val error: Throwable,
    private val onRetryButtonClick : () -> Unit,
) : Dialog(context){

    private val binding by lazy{ DialogErrorBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setCanceledOnTouchOutside(false)
        handleError()
    }

    private fun handleError(){
        when (error) {
            is HttpRetryException -> initViews("잠시후 다시 시도해주세요.", true)
            is SocketTimeoutException, is ConnectException -> initViews(
                "인터넷 연결을 확인해주세요.", true
            )
            else -> initViews("화면을 불러오는데 실패하였습니다.", false)
        }
    }

    private fun initViews(title: String, retryFlag: Boolean){
        binding.dialogErrorText.text = title
        if(!retryFlag){
            binding.retryButton.visibility = View.GONE
        }
        binding.retryButton.setOnClickListener{
            onRetryButtonClick()
            dismiss()
        }
    }
}