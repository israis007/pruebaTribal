package com.test.tribal.ui.base.callback

import android.content.DialogInterface

interface DialogCallBack {

    fun onAcceptClickListener(dialogInterface: DialogInterface)

    fun onCancelClickListener(dialogInterface: DialogInterface)

    fun onDismissClickListener(dialogInterface: DialogInterface)

}