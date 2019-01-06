package com.iuriirodyk.squarerepos.ui.fragment.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.iuriirodyk.squarerepos.app.SquareReposApp
import com.iuriirodyk.squarerepos.ui.activity.MainActivity
import com.iuriirodyk.squarerepos.ui.dialog.ProgressDialog
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseFragment : Fragment() {

    open val title = ""

    private var progressDialog: ProgressDialog? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).toolbar.title = title
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgressDialog()
        (activity?.application as SquareReposApp).leakRefWatcher.watch(this)
    }

    protected fun showProgressDialog(msg: String) {
        progressDialog = ProgressDialog(activity!!, msg).apply { show() }
    }

    protected fun hideProgressDialog() {
        progressDialog?.dismiss()
        progressDialog = null
    }
}