package it.playground.igeniusgithub.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _url = MutableLiveData<String>()
    val url: LiveData<String>
        get() = _url

    private val _authCodeSuccessful = MutableLiveData<Boolean>()
    val authCodeSuccessful: LiveData<Boolean>
        get() = _authCodeSuccessful

    init {
        viewModelScope.launch {
            _url.postValue(loginUseCase.constructCorrectUrl())
        }
    }

    fun retrieveAndSaveOAuthTokenForApi(code: String) {
            viewModelScope.launch {
                _authCodeSuccessful.postValue(loginUseCase.retrieveAndSaveToken(code))
            }
    }

}