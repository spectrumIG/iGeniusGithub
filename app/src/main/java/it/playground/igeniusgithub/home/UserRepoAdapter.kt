package it.playground.igeniusgithub.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.playground.igeniusgithub.R
import it.playground.igeniusgithub.domain.model.network.UserRepoUI
import java.util.*

class UserRepoAdapter : RecyclerView.Adapter<UserRepoAdapter.UserRepoViewHolder>() {

    private var data: List<UserRepoUI> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        return UserRepoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) = holder.bind(data[position])

    fun insertData(data: List<UserRepoUI>) {
        this.data = data
        notifyDataSetChanged()
    }

    class UserRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(item: UserRepoUI) = with(itemView) {

            val reposTiltle = this.findViewById<TextView>(R.id.repo_title_name)
            val repoDescription = this.findViewById<TextView>(R.id.repo_description)
            val repoLanguages = this.findViewById<TextView>(R.id.repo_languages)

            reposTiltle.text = item.repositoryName
            repoDescription.text = item.description
            repoLanguages.text = item.languages

            // TODO: Bind the data with View
            setOnClickListener {

                // TODO: go to detail repo


            }
        }
    }
}