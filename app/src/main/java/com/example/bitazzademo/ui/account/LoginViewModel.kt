package com.example.bitazzademo.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitazzademo.domain.AuthenticationItem
import com.example.bitazzademo.domain.LoginUseCase
import com.example.bitazzademo.domain.OMS_ID
import com.example.bitazzademo.domain.USER_KEY_TOKEN
import com.example.bitazzademo.domain.pref.PreferenceStoragable
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.bouncycastle.util.encoders.Base64

class LoginViewModel(
    private val prefs: PreferenceStoragable,
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _userData = LiveEvent<AuthenticationItem>()
    val userData: LiveData<AuthenticationItem>
        get() = _userData

    private val _isError = LiveEvent<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError

    private val _loading by lazy { LiveEvent<Boolean>() }
    val loading: LiveData<Boolean> by lazy { _loading }

    fun executeLogin(userName: String, password: String) {
        if(userName.isNotEmpty() && password.isNotEmpty()){
            val token = "Basic " + String(Base64.encode("$userName:$password".toByteArray()))
            viewModelScope.launch {
                loginUseCase.execute(token)
                    .onStart { showLoading() }
                    .catch { _isError.value = true }
                    .onCompletion { hideLoading() }
                    .collect {
                        onGetToken(it)
                    }
            }
        }
        else {
            _isError.value = true
        }
    }

    private fun onGetToken(item: AuthenticationItem) {
        _userData.value = item
    }

    private fun showLoading() {
        _loading.value = true
    }

    private fun hideLoading() {
        _loading.value = false
    }

    fun handleLoginData(authenticationItem: AuthenticationItem) {
        prefs.putString(USER_KEY_TOKEN, authenticationItem.token ?: "")
        prefs.putInt(OMS_ID, authenticationItem.oMSId ?: 1)
    }
}