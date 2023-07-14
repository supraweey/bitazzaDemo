package com.example.bitazzademo.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.bitazzademo.databinding.FragmentLoginBinding
import com.example.bitazzademo.domain.OMS_ID
import com.example.bitazzademo.domain.USER_KEY_TOKEN
import com.example.bitazzademo.domain.pref.PreferenceStoragable
import com.example.bitazzademo.ui.main.MainActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private val prefs: PreferenceStoragable by inject()
    private val viewModel by viewModel<LoginViewModel>()
    lateinit var binding: FragmentLoginBinding

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
            edtEmail.setText("suprawee.yimnium@gmail.com")
            edtPassword.setText("superFon@1")
            btnLogin.setOnClickListener {
                val username = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                viewModel.executeLogin(username, password)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onObserve()
    }

    private fun onObserve() {
        viewModel.userData.observe(requireActivity()) {
            prefs.putString(USER_KEY_TOKEN, it.token ?: "")
            prefs.putInt(OMS_ID, it.oMSId ?: 1)
            MainActivity.startActivity(requireContext())
        }
        viewModel.isError.observe(requireActivity()) {
            Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
        }
        viewModel.loading.observe(requireActivity()) {
            binding.progressBar.isVisible = it
        }
    }
}