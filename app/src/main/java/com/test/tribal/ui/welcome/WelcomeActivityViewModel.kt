package com.test.tribal.ui.welcome

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.test.tribal.R

class WelcomeActivityViewModel : ViewModel() {

    private val _selectedPage = MutableLiveData<Int>()
    val selectedPage : LiveData<Int> get() = _selectedPage
    private val _changeFragment = MutableLiveData<Boolean>()
    val changeFragment : LiveData<Boolean> get() = _changeFragment
    val title = MutableLiveData<String>()
    private var counter = 0
    private lateinit var context: Context
    private var isShowNext = false

    fun setFirstText(context: Context){
        this@WelcomeActivityViewModel.context = context
        title.postValue(context.getString(R.string.boarding_text1))
    }

    fun setShowNext(showNext: Boolean) {
        isShowNext = showNext
    }

    fun onClick() {
        _changeFragment.postValue(true)
        counter++
        updateText()
    }

    private fun updateText(){
        when(counter){
            1 -> title.postValue(context.getString(R.string.boarding_text2))
            2 -> title.postValue(context.getString(R.string.boarding_text3))
            else -> title.postValue(context.getString(R.string.boarding_text1))
        }
    }



    fun createPageListener() = object : ViewPager2.OnPageChangeCallback(){

        var selected = 0

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            selected = position
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            if (state == 0)
                _selectedPage.postValue(selected)
        }
    }

}