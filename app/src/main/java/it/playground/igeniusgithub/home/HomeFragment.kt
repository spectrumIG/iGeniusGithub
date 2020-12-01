package it.playground.igeniusgithub.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.playground.igeniusgithub.GithubApplication
import it.playground.igeniusgithub.R
import it.playground.igeniusgithub.databinding.FragmentHomeBinding
import it.playground.igeniusgithub.di.viewModels
import it.playground.igeniusgithub.domain.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class HomeFragment : Fragment(R.layout.fragment_home) {
    @Inject
    lateinit var viewModelProvider: Provider<HomeViewModel>

    private val homeViewModel by viewModels { viewModelProvider }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as GithubApplication).component.inject(this)
        val bind = FragmentHomeBinding.inflate(inflater, container, false)
        return bind.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.retrieveData()
        homeViewModel.userRepo.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Loading -> Timber.i("Show Loading spinner")
                is Result.Error -> Timber.i("Show error message")
                is Result.Success -> it.data.forEach { userRepoUI ->  Timber.i("ITEM: ${userRepoUI.repositoryName}\n\n LANGUAGE: ${userRepoUI
                    .languages}\n\n DESCRIPTION: ${userRepoUI.description}\n\n STARS: ${userRepoUI.starsNumber}") }
            }
        })
    }
}