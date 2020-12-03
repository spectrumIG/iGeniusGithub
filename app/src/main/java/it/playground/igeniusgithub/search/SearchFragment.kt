package it.playground.igeniusgithub.search

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import it.playground.igeniusgithub.GithubApplication
import it.playground.igeniusgithub.R
import it.playground.igeniusgithub.databinding.FragmentSearchBinding
import it.playground.igeniusgithub.di.viewModels
import it.playground.igeniusgithub.domain.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import javax.inject.Provider

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelProvider: Provider<SearchViewModel>

    private val searchViewModel by viewModels { viewModelProvider }

    private var searchForUserFlag = false
    private lateinit var bind: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (requireActivity().application as GithubApplication).component.inject(this)

        bind = FragmentSearchBinding.inflate(inflater, container, false)

        adapter = SearchAdapter()

        bind.searchRecycler.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonSearch = bind.buttonSearch
        bind.searchSwipe.setOnRefreshListener {
            makeSearch()
        }
        buttonSearch.setOnClickListener {
//            adapter.clearData()
            bind.searchSwipe.isRefreshing = true
            makeSearch()
        }

        searchViewModel.searchRepo.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> bind.searchSwipe.isRefreshing = true

                is Result.Error -> {
                    bind.searchSwipe.isRefreshing = false
                    disableRefreshAndShowCourtesyText()
                }
                is Result.Success -> {
                    if(it.data.isEmpty()) {
                        disableRefreshAndShowCourtesyText()
                    } else {
                        disableRefreshAndShowData()
                        adapter.insertData(it.data.toList())
                    }
                }
            }
        }
        searchViewModel.userRepo.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> bind.searchSwipe.isRefreshing = true

                is Result.Error -> {
                    bind.searchSwipe.isRefreshing = false
                    disableRefreshAndShowCourtesyText()
                }
                is Result.Success -> {
                    if(it.data.isEmpty()) {
                        disableRefreshAndShowCourtesyText()
                    } else {
                        disableRefreshAndShowData()
                        adapter.insertData(it.data.toList())
                    }
                }
            }

        }
    }

    @InternalCoroutinesApi
    private fun makeSearch() {
        if(searchForUserFlag) {
            searchViewModel.searchUserFor(bind.textInputEditTextOutlined.text.toString())
        } else {
            searchViewModel.searchRepoFor(bind.textInputEditTextOutlined.text.toString())

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_option_selection, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_repo_opt -> {
                item.isChecked = !item.isChecked
                searchForUserFlag = !item.isChecked
                true
            }
            R.id.search_user_opt -> {
                item.isChecked = !item.isChecked
                searchForUserFlag = item.isChecked
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun disableRefreshAndShowCourtesyText() {
        bind.searchSwipe.isRefreshing = false

        bind.searchRecycler.visibility = View.GONE
        bind.emptyOrErrorDataText.visibility = View.VISIBLE
    }

    private fun disableRefreshAndShowData() {
        bind.searchSwipe.isRefreshing = false

        bind.searchRecycler.visibility = View.VISIBLE
        bind.emptyOrErrorDataText.visibility = View.GONE
    }
}
