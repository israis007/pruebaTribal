package com.test.tribal.ui.welcome.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.shape.CornerFamily
import com.test.tribal.R
import com.test.tribal.databinding.FragmentOnboardingBinding
import com.test.tribal.tools.ui.InverseCornerTreatment
import com.test.tribal.ui.base.FragmentBase

class BoardingFragment : FragmentBase() {

    companion object {
        fun newInstance() = BoardingFragment()
    }

    private lateinit var viewModel: BoardingFragmentViewModel
    private val binding : FragmentOnboardingBinding by lazy {
        DataBindingUtil.inflate(LayoutInflater.from(this@BoardingFragment.requireContext()), R.layout.fragment_onboarding, null, false)
    }
    private var image       : Int       = 0
    private var background  : Int       = 0
    private var isScalable  : Boolean   = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragOnboardAcivImage.scaleType = if (isScalable) ImageView.ScaleType.FIT_XY else ImageView.ScaleType.FIT_CENTER
        binding.fragOnboardAcivImage.setImageResource(image)
        binding.fragOnboardAcivBack.setImageResource(background)
        viewModel = ViewModelProvider(this)[BoardingFragmentViewModel::class.java]
    }

    override fun setListeners() {

    }

    override fun setObservers() {

    }

    fun setImage(@DrawableRes image: Int){
        this@BoardingFragment.image = image
    }

    fun setBackground(@DrawableRes background : Int) {
        this@BoardingFragment.background = background
    }

    fun setScalable(isScalable: Boolean){
        this@BoardingFragment.isScalable = isScalable
    }

}