package it.playground.igeniusgithub.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.playground.igeniusgithub.GithubApplication
import it.playground.igeniusgithub.R
import it.playground.igeniusgithub.databinding.FragmentHomeBinding
import it.playground.igeniusgithub.di.viewModels
import it.playground.igeniusgithub.domain.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Provider

class HomeFragment : Fragment(R.layout.fragment_home) {
    @Inject
    lateinit var viewModelProvider: Provider<HomeViewModel>

    private val homeViewModel by viewModels { viewModelProvider }
    private lateinit var bind: FragmentHomeBinding
    private lateinit var userRepoRecycler: RecyclerView
    private lateinit var userRepoAdapter: UserRepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity().application as GithubApplication).component.inject(this)
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        userRepoRecycler = bind.userRepoRecycler
        userRepoAdapter = UserRepoAdapter()

        userRepoRecycler.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = userRepoAdapter
        }


        return bind.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.userRepoSwipe.isRefreshing = true
        homeViewModel.retrieveData()

        homeViewModel.userRepo.observe(viewLifecycleOwner, {
                  when (it) {

                is Result.Loading -> bind.userRepoSwipe.isRefreshing = true

                is Result.Error -> {
                    bind.userRepoSwipe.isRefreshing = false
                    disableRefreshAndShowCourtesyText()
                }
                is Result.Success -> {
                    if(it.data.isEmpty()) {
                        disableRefreshAndShowCourtesyText()
                    } else {
                        disableRefreshAndShowData()
                        userRepoAdapter.insertData(it.data.toList())
                    }
                }
            }
        })

        bind.userRepoSwipe.setOnRefreshListener {
            homeViewModel.retrieveData()
        }

    }

    private fun disableRefreshAndShowCourtesyText() {
        bind.userRepoSwipe.isRefreshing = false

        bind.userRepoRecycler.visibility = View.GONE
        bind.emptyOrErrorDataText.visibility = View.VISIBLE
    }

    private fun disableRefreshAndShowData() {
        bind.userRepoSwipe.isRefreshing = false

        bind.userRepoRecycler.visibility = View.VISIBLE
        bind.emptyOrErrorDataText.visibility = View.GONE
    }
}