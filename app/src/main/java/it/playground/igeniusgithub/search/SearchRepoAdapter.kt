package it.playground.igeniusgithub.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import it.playground.igeniusgithub.R
import it.playground.igeniusgithub.domain.model.network.SearchRepositoryUI
import it.playground.igeniusgithub.domain.model.network.SearchUserUI
import it.playground.igeniusgithub.domain.model.network.UiData

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.BaseViewHolder>() {

    private var items: MutableList<UiData> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SearchUserUI -> R.layout.search_user_card_item
            else -> R.layout.search_repo_card_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            R.layout.search_repo_card_item -> SearchRepoViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.search_repo_card_item, parent, false))
            else -> SearchUserViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.search_user_card_item, parent, false))
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.bind(items[position])

    fun insertData(data: List<UiData>) {
        this.items.clear()
        this.items = data.toMutableList()
        notifyDataSetChanged()
    }

    fun clearData() {
        this.items.clear()
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: UiData)
    }

    class SearchRepoViewHolder(itemView: View) : BaseViewHolder(itemView) {

        override fun bind(data: UiData) = with(itemView) {
            val item = data as SearchRepositoryUI
            val avatarImage = itemView.findViewById<ImageView>(R.id.repo_avatar_image)
            val userName = itemView.findViewById<TextView>(R.id.repo_user_name)
            val repoTitle = itemView.findViewById<TextView>(R.id.repo_title_name)
            val repoDescription = itemView.findViewById<TextView>(R.id.repo_description)
            val repoLanguages = itemView.findViewById<TextView>(R.id.repo_languages)

            avatarImage.load(item.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_person_24)
                transformations(CircleCropTransformation())
            }
            userName.text = item.userLoginId
            repoTitle.text = item.repositoryName
            repoDescription.text = item.description
            repoLanguages.text = item.languages


            setOnClickListener {
                val bundle = bundleOf("repoDetail" to item)
                itemView.findNavController().navigate(R.id.fromSearchToDetail,bundle)
            }
        }
    }

    class SearchUserViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(data: UiData) = with(itemView) {
            val item = data as SearchUserUI
            val avatarImage = itemView.findViewById<ImageView>(R.id.user_search_avatar)
            val userLogin = itemView.findViewById<TextView>(R.id.user_login_id)
            val userBio = itemView.findViewById<TextView>(R.id.user_bio)
            val userName = itemView.findViewById<TextView>(R.id.user_name_search)

            avatarImage.load(item.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_person_24)
                transformations(CircleCropTransformation())
            }

            userLogin.text = item.id
            userBio.text = item.bio
            userName.text = item.name


            setOnClickListener {
                val bundle = bundleOf("userDetail" to item)
                itemView.findNavController().navigate(R.id.fromSearchToDetail,bundle)
            }
        }
    }
}