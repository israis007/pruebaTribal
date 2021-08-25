package com.test.tribal.ui.welcome

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.test.tribal.R
import com.test.tribal.databinding.ActivityWelcomeBinding
import com.test.tribal.models.Customization
import com.test.tribal.ui.base.ActivityBase
import com.test.tribal.ui.mainscreen.MainActivityView

class WelcomeActivity : ActivityBase() {

    private val binding: ActivityWelcomeBinding by lazy {
        DataBindingUtil.setContentView(this@WelcomeActivity, R.layout.activity_welcome)
    }
    private lateinit var viewModel: WelcomeActivityViewModel
    private lateinit var adapter: BoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[WelcomeActivityViewModel::class.java]
        viewModel.setFirstText(this)

        binding.apply {
            welcomeVM       = viewModel
            lifecycleOwner  = this@WelcomeActivity
        }

        adapter = BoardingAdapter(this@WelcomeActivity, listOf(
            Customization().apply {
                image       = R.drawable.plant_1
                background  = R.drawable.back_1
                isScalable  = false
            },
            Customization().apply {
                image       = R.drawable.plant_2
                background  = R.drawable.back_2
                isScalable  = true
            },
            Customization().apply {
                image       = R.drawable.plant_4
                background  = R.drawable.back_1
                isScalable  = true
            }
        ))
        setContentView(binding.root)

        setObservers()
        setListeners()

        binding.actWelVp2Pager.adapter = adapter
        binding.actWelVp2Pager.registerOnPageChangeCallback(viewModel.createPageListener())
        binding.actWelcWdiIndicator.setViewPager2(binding.actWelVp2Pager)

    }

    override fun setListeners() {

    }

    override fun setObservers() {
        viewModel.selectedPage.observe(this@WelcomeActivity) {
            val page = it ?: return@observe
            when(page){
                0 -> {
                    viewModel.title.postValue(getString(R.string.boarding_text1))
                    viewModel.setShowNext(false)
                }
                1 -> {
                    viewModel.title.postValue(getString(R.string.boarding_text2))
                    viewModel.setShowNext(false)
                }
                2 -> {
                    viewModel.title.postValue(getString(R.string.boarding_text3))
                    viewModel.setShowNext(true)
                }
            }
        }

        viewModel.changeFragment.observe(this@WelcomeActivity) {
            val change = it ?: return@observe
            if (change && binding.actWelVp2Pager.currentItem != 2) {
                binding.actWelVp2Pager.currentItem = binding.actWelVp2Pager.currentItem + 1
            } else
                super.launchActivity(MainActivityView::class.java)
        }
    }
}