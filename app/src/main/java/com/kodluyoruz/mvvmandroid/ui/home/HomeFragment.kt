package com.kodluyoruz.mvvmandroid.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kodluyoruz.mvvmandroid.R
import com.kodluyoruz.mvvmandroid.databinding.FragmentHomeBinding
import com.kodluyoruz.mvvmandroid.utils.AuthStateListener
import com.kodluyoruz.mvvmandroid.utils.Resource
import com.kodluyoruz.mvvmandroid.utils.gone
import com.kodluyoruz.mvvmandroid.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()
    private var authStateListener: AuthStateListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        authStateListener = context as AuthStateListener
    }

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
        viewModel.getUserListLiveData().observe(viewLifecycleOwner, Observer { list ->
            Log.v("HomeFragment", "$list")
            list.forEach {
                _binding.homeTextView.text = "${_binding.homeTextView.text}\n ${it.name}"
            }
        })

        _binding.logoutButton.setOnClickListener {
            viewModel.removeToken()
            authStateListener?.logout()
        }

        viewModel.listUser()

//        viewModel.fetchRickMortList().observe(viewLifecycleOwner, {
//            when (it.status) {
//                Resource.Status.LOADING -> {
//                    _binding.progressBar.show()
//                }
//                Resource.Status.SUCCESS -> {
//                    _binding.progressBar.gone()
//                    _binding.homeTextView.text = "Count: ${it.data?.characters?.size}"
//                }
//                Resource.Status.ERROR -> {
//                    _binding.progressBar.gone()
//                    _binding.homeTextView.setTextColor(resources.getColor(R.color.red))
//                    _binding.homeTextView.text = "Error: ${it?.message}"
//                }
//            }
//        })
    }
}