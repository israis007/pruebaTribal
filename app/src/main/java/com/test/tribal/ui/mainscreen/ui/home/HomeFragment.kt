package com.test.tribal.ui.mainscreen.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.test.tribal.R
import com.test.tribal.databinding.FragmentHomeBinding
import com.test.tribal.models.Photos
import com.test.tribal.rest.objects.StatusType
import com.test.tribal.ui.base.ActivityBase
import com.test.tribal.ui.base.FragmentBase
import com.test.tribal.ui.base.callback.DialogCallBack
import com.test.tribal.ui.mainscreen.HomeActivity

class HomeFragment : FragmentBase() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        adapter = HomeAdapter(requireContext(), emptyList(), object: HomeAdapter.HomeViewListener{
            override fun onClickLeague(player: Photos) {
                (requireActivity() as ActivityBase).showToastMessage(getString(R.string.wait_image))
            }
        })

        binding.fraHomeRv.apply {
            layoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.COLUMN
                justifyContent = JustifyContent.SPACE_BETWEEN
            }
            //layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
            isNestedScrollingEnabled = true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setListeners()
        setObservers()

        homeViewModel.getPhotosFromServer(1)
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
            val act = (requireActivity() as ActivityBase)
            act.showLoading(false)
            when (response.statusType) {
                StatusType.SUCCESS -> {
                    adapter.onAddItem(response.data!!)
                }
                StatusType.FAILED -> {}
                StatusType.ERROR -> {
                    act.showErrorMessage(getString(R.string.error_get_images), response.message ?: "", object: DialogCallBack{
                        override fun onAcceptClickListener(dialogInterface: DialogInterface) {
                            dialogInterface.dismiss()
                        }
                        override fun onCancelClickListener(dialogInterface: DialogInterface) {}
                        override fun onDismissClickListener(dialogInterface: DialogInterface) {}
                    })
                }
                StatusType.LOADING -> {
                    act.showLoading(true)
                }
            }
        }
    }
}