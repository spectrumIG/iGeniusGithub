package it.playground.igeniusgithub.login

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _url = MutableLiveData<String>()

    val url: LiveData<String>
        get() = _url

    init{
        viewModelScope.launch {
          _url.postValue(loginUseCase.constructCorrectUrl())
        }
    }


}