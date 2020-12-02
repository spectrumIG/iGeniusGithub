package it.playground.igeniusgithub.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    @InternalCoroutinesApi
    fun searchRepoFor(filter: String) {
        viewModelScope.launch {
            searchUseCase.searchRepoBy(filter) // <-- Sbagliato, fare uno solo dei due
        }
    }
    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    fun searchUserFor(filter: String) {
        viewModelScope.launch {
            searchUseCase.searchUserBy(filter) // <-- Sbagliato, fare uno solo dei due
        }
    }

}