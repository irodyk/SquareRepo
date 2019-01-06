package com.iuriirodyk.squarerepos.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.iuriirodyk.squarerepos.R
import kotlinx.android.synthetic.main.dialog_progress.*

class ProgressDialog(context: Context, private val msg: String) : Dialog(context) {

    init {
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)

        tv_message.text = msg
    }
}