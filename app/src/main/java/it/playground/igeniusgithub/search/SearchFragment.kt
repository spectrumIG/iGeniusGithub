package it.playground.igeniusgithub.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import it.playground.igeniusgithub.GithubApplication
import it.playground.igeniusgithub.R
import it.playground.igeniusgithub.databinding.FragmentSearchBinding
import it.playground.igeniusgithub.di.viewModels
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import javax.inject.Provider

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelProvider: Provider<SearchViewModel>

    private val searchViewModel by viewModels { viewModelProvider }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as GithubApplication).component.inject(this)

        val bind = FragmentSearchBinding.inflate(inflater,container,false)

        return bind.root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.searchRepoFor("test")
        searchViewModel.searchUserFor("test")
    }


}