package com.test.tribal.ui.mainscreen.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.tribal.databinding.FragmentHomeBinding
import com.test.tribal.rest.objects.StatusType
import com.test.tribal.ui.base.ActivityBase
import com.test.tribal.ui.base.FragmentBase
import com.test.tribal.ui.mainscreen.HomeActivity

class HomeFragment : FragmentBase() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setListeners() {

    }

    override fun setObservers() {

        homeViewModel.response.observe(viewLifecycleOwner) {
            val response = it ?: return@observe
            (requireActivity() as ActivityBase).showLoading(true)
            when (response.statusType) {
                StatusType.SUCCESS -> {
                    (requireActivity() as ActivityBase).showLoading(true)
                }
                StatusType.FAILED -> TODO()
                StatusType.ERROR -> TODO()
                StatusType.LOADING -> {
                    (requireActivity() as ActivityBase).showLoading(true)
                }
            }
        }
    }
}