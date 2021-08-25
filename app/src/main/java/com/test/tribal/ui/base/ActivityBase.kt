package com.test.tribal.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.test.tribal.R
import com.test.tribal.databinding.ErrorDialogBinding
import com.test.tribal.ui.base.callback.DialogCallBack
import com.test.tribal.ui.base.viewmodels.BaseErrorViewModel

const val ARG_EXTRAS = "argsExtras"

abstract class ActivityBase : AppCompatActivity() {

    private val dialogLoading by lazy {
        val dialog = AlertDialog.Builder(this@ActivityBase, R.style.CustomDialog)
        dialog.setView(R.layout.dialog_loading)
        dialog.setCancelable(false)
        dialog.create()
    }

    fun showLoading(loading: Boolean){
        if (loading){
            if (!dialogLoading.isShowing)
                dialogLoading.show()
        } else {
            if (dialogLoading.isShowing)
                dialogLoading.dismiss()
        }
    }

    fun showErrorMessage(title: String, message: String, dialogCallBack : DialogCallBack){
        val dialog = AlertDialog.Builder(this@ActivityBase)
        val vm = ViewModelProvider(this@ActivityBase)[BaseErrorViewModel::class.java]
        val binding : ErrorDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this@ActivityBase), R.layout.error_dialog, null, false)
        binding.apply {
            errorDialog     = vm
            lifecycleOwner  = this@ActivityBase
        }
        dialog.setView(binding.root)
        dialog.setCancelable(false)
        dialog.setPositiveButton(R.string.generic_accept) { dialog1, _ ->
            dialogCallBack.onAcceptClickListener(dialog1)
        }
        vm.setTitle(title)
        vm.setMessage(message)
        dialog.setOnDismissListener {
            dialogCallBack.onDismissClickListener(it)
        }
        dialog.create().show()
    }

    fun showInfoMessage(title: String, message: String, dialogCallBack : DialogCallBack){
        val dialog = AlertDialog.Builder(this@ActivityBase)
        val vm = ViewModelProvider(this@ActivityBase)[BaseErrorViewModel::class.java]
        val binding : ErrorDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this@ActivityBase), R.layout.error_dialog, null, false)
        binding.apply {
            errorDialog     = vm
            lifecycleOwner  = this@ActivityBase
        }
        dialog.setView(binding.root)
        dialog.setCancelable(false)
        dialog.setPositiveButton(R.string.generic_accept) { dialog1, _ ->
            dialogCallBack.onAcceptClickListener(dialog1)
        }
        dialog.setNegativeButton(R.string.generic_cancel) { dialog2, _ ->
            dialogCallBack.onCancelClickListener(dialog2)
        }
        vm.setTitle(title)
        vm.setMessage(message)
        dialog.setOnDismissListener {
            dialogCallBack.onDismissClickListener(it)
        }
        dialog.create().show()
    }

    fun showInfoMessage(title: String, message: String, @StringRes positiveText: Int, @StringRes negativeText: Int, dialogCallBack : DialogCallBack){
        val dialog = AlertDialog.Builder(this@ActivityBase)
        val vm = ViewModelProvider(this@ActivityBase)[BaseErrorViewModel::class.java]
        val binding : ErrorDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this@ActivityBase), R.layout.error_dialog, null, false)
        binding.apply {
            errorDialog     = vm
            lifecycleOwner  = this@ActivityBase
        }
        dialog.setView(binding.root)
        dialog.setCancelable(false)
        dialog.setPositiveButton(positiveText) { dialog1, _ ->
            dialogCallBack.onAcceptClickListener(dialog1)
        }
        dialog.setNegativeButton(negativeText) { dialog2, _ ->
            dialogCallBack.onCancelClickListener(dialog2)
        }
        vm.setTitle(title)
        vm.setMessage(message)
        dialog.setOnDismissListener {
            dialogCallBack.onDismissClickListener(it)
        }
        dialog.create().show()
    }

    fun <T> launchActivity(clazz: Class<T>){
        val intent = Intent(this@ActivityBase, clazz).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        startActivity(intent)
    }

    fun <T> launchActivity(clazz: Class<T>, bundle: Bundle){
        val intent = Intent(this@ActivityBase, clazz).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        intent.putExtra(ARG_EXTRAS, bundle)
        startActivity(intent)
    }

    fun showToastMessage(message : String) =
        Toast.makeText(this@ActivityBase, message, Toast.LENGTH_LONG).show()

    fun showToastMessage(message : String, duration: Int) =
        Toast.makeText(this@ActivityBase, message, duration).show()

    abstract fun setListeners()

    abstract fun setObservers()

}