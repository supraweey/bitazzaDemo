package com.example.bitazzademo.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bitazzademo.databinding.FragmentLoginBinding
import com.example.bitazzademo.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: Fragment() {
    private val viewModel by viewModel<LoginViewModel>()
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            MainActivity.startActivity(requireContext())
        }
    }

    private fun onObserve(){

    }
}