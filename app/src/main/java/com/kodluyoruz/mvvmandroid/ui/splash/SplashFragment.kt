package com.kodluyoruz.mvvmandroid.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kodluyoruz.mvvmandroid.R
import com.kodluyoruz.mvvmandroid.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var _binding: FragmentSplashBinding

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeNavigationLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                "auth" -> {
                    findNavController().navigate(R.id.action_splashFragment_to_authFragment)
                }
                "home" -> {
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }
            }
        })
        viewModel.checkTokenAndNavigation()

    }
}