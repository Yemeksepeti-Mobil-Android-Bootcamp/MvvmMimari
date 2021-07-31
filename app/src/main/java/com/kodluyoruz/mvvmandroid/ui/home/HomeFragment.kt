package com.kodluyoruz.mvvmandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kodluyoruz.mvvmandroid.databinding.FragmentHomeBinding
import com.kodluyoruz.mvvmandroid.utils.Resource

class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchRickMortList().observe(viewLifecycleOwner, {
            when(it.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {

                }
                Resource.Status.ERROR -> {

                }
            }
        })
    }
}