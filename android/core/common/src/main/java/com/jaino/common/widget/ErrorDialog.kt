package com.jaino.common.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.jaino.common.databinding.DialogErrorBinding
import java.io.IOException
import java.net.HttpRetryException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorDialog(
    context: Context,
    private val error: Throwable,
    private val onRetryButtonClick : () -> Unit,
) : Dialog(context){

    private val binding by lazy{ DialogErrorBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleError()
    }

    private fun handleError(){
        when (error) {
            is HttpRetryException, is SocketTimeoutException -> initViews("화면을 불러오지 못했습니다.", true)
            is UnknownHostException -> initViews("인터넷 연결을 확인해주세요.", true)
            is IOException -> initViews("문제가 발생하였습니다.", false)
            else -> initViews("문제가 발생하였습니다.", false)
        }
    }

    private fun initViews(title: String, retryFlag: Boolean){
        binding.dialogErrorText.text = title
        if(!retryFlag){
            binding.retryButton.visibility = View.GONE
        }
        binding.retryButton.setOnClickListener{
            onRetryButtonClick()
        }
    }
}