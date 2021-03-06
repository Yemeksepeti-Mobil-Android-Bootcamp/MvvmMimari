package com.kodluyoruz.mvvmandroid.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kodluyoruz.mvvmandroid.R
import com.kodluyoruz.mvvmandroid.databinding.FragmentLoginBinding
import com.kodluyoruz.mvvmandroid.utils.Resource
import com.kodluyoruz.mvvmandroid.utils.gone
import com.kodluyoruz.mvvmandroid.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var _binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.loginButton.setOnClickListener {
            val email = _binding.editTextTextEmailAddress.text.toString()
            val password = _binding.editTextTextPassword.text.toString()
            viewModel.login(email, password)
                .observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            _binding.progressBar.show()
                        }
                        Resource.Status.SUCCESS -> {
                            _binding.progressBar.gone()
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        }
                        Resource.Status.ERROR -> {
                            _binding.progressBar.gone()
                            val dialog = AlertDialog.Builder(context)
                                .setTitle("Error")
                                .setMessage("${it.message}")
                                .setPositiveButton("ok") { dialog, button ->
                                    dialog.dismiss()
                                }
                            dialog.show()
                        }
                    }
                })
        }
    }
}