package com.test.tribal.ui.base.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseErrorViewModel : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title : LiveData<String> get() = _title

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message

    fun setTitle(title : String) = _title.postValue(title)

    fun setMessage(message : String) = _message.postValue(message)

}