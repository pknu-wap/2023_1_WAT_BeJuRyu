package com.jaino.common.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.jaino.common.R

class LoadingDialog(context: Context): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
    }

}