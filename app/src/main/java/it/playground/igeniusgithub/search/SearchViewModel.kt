package it.playground.igeniusgithub.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.model.network.SearchRepoMapper
import it.playground.igeniusgithub.domain.model.network.SearchRepositoryUI
import it.playground.igeniusgithub.domain.model.network.SearchUserMapper
import it.playground.igeniusgithub.domain.model.network.SearchUserUI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {
    private val _searchUser = MutableLiveData<Result<List<SearchUserUI>>>()
    val userRepo: LiveData<Result<List<SearchUserUI>>>
        get() = _searchUser

    private val _searchRepo = MutableLiveData<Result<List<SearchRepositoryUI>>>()
    val searchRepo: LiveData<Result<List<SearchRepositoryUI>>>
        get() = _searchRepo

    @InternalCoroutinesApi
    fun searchRepoFor(filter: String) {
        viewModelScope.launch {

            when (val searchRepoBy = searchUseCase.searchRepoBy(filter)) {

                is Result.Loading -> _searchRepo.postValue(Result.Loading)

                is Result.Error -> _searchRepo.postValue(Result.Error(searchRepoBy.exception))

                is Result.Success -> {
                    val uiResult = ArrayList<SearchRepositoryUI>()
                    searchRepoBy.data.forEach {
                        uiResult.add(SearchRepoMapper().mapFrom(it))
                    }
                    _searchRepo.postValue(Result.Success(uiResult))
                }
            }

        }
    }
    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    fun searchUserFor(filter: String) {
        viewModelScope.launch {

            when (val searchUserBy = searchUseCase.searchUserBy(filter)) {
                is Result.Loading -> _searchUser.postValue(Result.Loading)
                is Result.Error -> _searchUser.postValue(Result.Error(searchUserBy.exception))
                is Result.Success -> {
                    val uiResult = ArrayList<SearchUserUI>()
                    searchUserBy.data.forEach {
                        uiResult.add(SearchUserMapper().mapFrom(it))
                    }
                    _searchUser.postValue(Result.Success(uiResult))
                }
            }
        }
    }

}