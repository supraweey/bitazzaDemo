package com.example.bitazzademo.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitazzademo.domain.AuthenticationItem
import com.example.bitazzademo.domain.LoginUseCase
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.bouncycastle.util.encoders.Base64

class LoginViewModel(private val loginUseCase: LoginUseCase): ViewModel() {
    private val _token = LiveEvent<String>()
    val token: LiveData<String>
        get() = _token

    private val _isError = LiveEvent<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError

    fun executeLogin(userName: String, password: String){
        val token = "Basic " + String(Base64.encode("$userName:$password".toByteArray()))
        viewModelScope.launch {
            loginUseCase.execute(token)
                .onStart {  }
                .catch { _isError.value = true }
                .collect{
                    onGetToken(it)
                }
        }
    }

    private fun onGetToken(item: AuthenticationItem){
        item.token?.let {
            _token.value = it
        }

    }
}