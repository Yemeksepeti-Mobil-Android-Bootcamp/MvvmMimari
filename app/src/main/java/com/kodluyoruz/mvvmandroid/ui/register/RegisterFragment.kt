package com.kodluyoruz.mvvmandroid.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kodluyoruz.mvvmandroid.databinding.FragmentRegisterBinding
import com.kodluyoruz.mvvmandroid.utils.Resource
import com.kodluyoruz.mvvmandroid.utils.gone
import com.kodluyoruz.mvvmandroid.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var _binding: FragmentRegisterBinding


    private val viewModel: RegisterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.button.setOnClickListener {
            val email = _binding.editTextTextEmailAddress2.text.toString()
            val name = _binding.editTextTextPersonName.text.toString()
            val password = _binding.editTextTextPassword2.text.toString()
            viewModel.register(email, name, password)
                .observe(viewLifecycleOwner, {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            _binding.progressBar.show()
                        }
                        Resource.Status.SUCCESS -> {
                            _binding.progressBar.gone()
                        }
                        Resource.Status.ERROR -> {
                            _binding.progressBar.gone()
                        }
                    }
                })
        }
    }
}