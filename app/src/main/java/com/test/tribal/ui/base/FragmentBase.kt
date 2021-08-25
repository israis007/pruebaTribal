package com.test.tribal.ui.base

import androidx.fragment.app.Fragment

abstract class FragmentBase : Fragment() {

    abstract fun setListeners()

    abstract fun setObservers()

}