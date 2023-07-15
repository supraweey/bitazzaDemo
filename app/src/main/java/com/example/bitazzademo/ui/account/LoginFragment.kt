package com.example.bitazzademo.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.bitazzademo.R
import com.example.bitazzademo.databinding.FragmentLoginBinding
import com.example.bitazzademo.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            edtEmail.setText(String())
            edtPassword.setText(String())
            btnLogin.setOnClickListener {
                viewModel.executeLogin(
                    userName = edtEmail.text.toString(),
                    password = edtPassword.text.toString()
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onObserve()
    }

    private fun onObserve() {
        viewModel.apply {
            userData.observe(requireActivity()) {
                handleLoginData(it)
                MainActivity.startActivity(requireContext())
            }

            isError.observe(requireActivity()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.message_login_error),
                    Toast.LENGTH_LONG
                ).show()
            }

            loading.observe(requireActivity()) {
                binding.progressBar.isVisible = it
            }
        }
    }
}