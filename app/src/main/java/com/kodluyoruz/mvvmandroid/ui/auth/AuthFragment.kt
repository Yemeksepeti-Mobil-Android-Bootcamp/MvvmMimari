package com.kodluyoruz.mvvmandroid.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kodluyoruz.mvvmandroid.R
import com.kodluyoruz.mvvmandroid.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {
    private lateinit var _binding: FragmentAuthBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_loginFragment)
        }
        _binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registerFragment)
        }
    }
}