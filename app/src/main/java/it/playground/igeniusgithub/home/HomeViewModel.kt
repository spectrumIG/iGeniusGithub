package it.playground.igeniusgithub.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.model.network.UserRepoMapper
import it.playground.igeniusgithub.domain.model.network.UserRepoUI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {
    private val _userRepo = MutableLiveData<Result<List<UserRepoUI>>>()
    val userRepo: LiveData<Result<List<UserRepoUI>>>
        get() = _userRepo

    @ExperimentalCoroutinesApi
    fun retrieveData() {
        viewModelScope.launch {
            when (val allUserRepo = homeUseCase.getAllUserRepo()) {
                is Result.Loading -> Result.Loading

                is Result.Error -> _userRepo.postValue(Result.Error(allUserRepo.exception))

                is Result.Success -> {
                    val uiResult = ArrayList<UserRepoUI>()
                    allUserRepo.data.forEach { element ->
                        uiResult.add(UserRepoMapper().mapFrom(element))
                    }
                    _userRepo.postValue(Result.Success(uiResult))
                }

            }
        }
    }
}