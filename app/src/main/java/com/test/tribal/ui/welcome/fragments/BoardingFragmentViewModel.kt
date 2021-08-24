package com.test.tribal.ui.welcome.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BoardingFragmentViewModel : ViewModel() {

    val text = MutableLiveData<String>()
    val image = MutableLiveData<Int>()

}