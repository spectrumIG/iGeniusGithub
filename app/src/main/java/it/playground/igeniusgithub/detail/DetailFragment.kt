package it.playground.igeniusgithub.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import it.playground.igeniusgithub.R
import it.playground.igeniusgithub.databinding.DetailFragmentBinding
import it.playground.igeniusgithub.domain.model.network.SearchRepositoryUI
import it.playground.igeniusgithub.domain.model.network.SearchUserUI
import it.playground.igeniusgithub.domain.model.network.UserRepoUI

class DetailFragment : Fragment(R.layout.detail_fragment) {
    private lateinit var bind: DetailFragmentBinding
    private var userRepoDetail: UserRepoUI? = null
    private var searchRepoDetail: SearchRepositoryUI? = null
    private var searchUserDetail: SearchUserUI? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = DetailFragmentBinding.inflate(inflater, container, false)

        userRepoDetail = arguments?.getParcelable("userRepo")
        searchRepoDetail = arguments?.getParcelable("repoDetail")
        searchUserDetail = arguments?.getParcelable("userDetail")


        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(userRepoDetail != null) {
            bind.detailAvatarImage.visibility = View.GONE
            bind.detailSubtitleContainer.visibility = View.GONE
            bind.detailDescription.text = userRepoDetail!!.description
            bind.detailRepoTitleName.text = userRepoDetail!!.repositoryName
            bind.detailRepoLanguages.text = userRepoDetail!!.languages

        } else if(searchRepoDetail != null) {
            bind.detailAvatarImage.load(searchRepoDetail!!.avatarUrl){
                crossfade(true)
                placeholder(R.drawable.ic_baseline_person_24)
                transformations(CircleCropTransformation())
            }
            bind.detailDescription.text = searchRepoDetail!!.description
            bind.starcounterContainer.visibility = View.VISIBLE
            bind.starcounterValue.text = searchRepoDetail!!.starsNumber.toString()
            bind.detailRepoLanguages.text = searchRepoDetail!!.languages
            bind.detailRepoTitleName.text = searchRepoDetail!!.repositoryName

        } else {
            bind.detailAvatarImage.load(searchUserDetail!!.avatarUrl){
                crossfade(true)
                placeholder(R.drawable.ic_baseline_person_24)
                transformations(CircleCropTransformation())
            }
            bind.detailDescription.text = searchUserDetail!!.bio
            bind.detailNumberContainer.visibility = View.VISIBLE
            bind.detailFollowerLabel.text = searchUserDetail!!.totalFollower.toString()
            bind.detailFollowing.text = searchUserDetail!!.totalFollowing.toString()
            bind.detailSubtitleContainer.visibility = View.VISIBLE
            bind.detailUserName.text = searchUserDetail!!.id
            bind.detailRepoLanguages.visibility = View.GONE

        }
    }
}