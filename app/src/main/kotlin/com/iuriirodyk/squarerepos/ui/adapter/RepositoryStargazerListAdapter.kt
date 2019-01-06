package com.iuriirodyk.squarerepos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iuriirodyk.squarerepos.BR
import com.iuriirodyk.squarerepos.R
import com.iuriirodyk.squarerepos.databinding.StargazersListItemBinding
import com.iuriirodyk.squarerepos.viewmodel.RepositoryDetailViewModel

class RepositoryStargazerListAdapter(var items: List<RepositoryDetailViewModel.Stargazer>)
: RecyclerView.Adapter<RepositoryStargazerListAdapter.StargazerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StargazerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<StargazersListItemBinding>(
            inflater, R.layout.stargazers_list_item, parent, false)
        return StargazerViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: StargazerViewHolder, position: Int)
            = holder.bind(items[position])

    inner class StargazerViewHolder(private val binding: ViewDataBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepositoryDetailViewModel.Stargazer) {
            binding.setVariable(BR.stargazer, item)
            binding.executePendingBindings()
        }
    }

    companion object {
        @JvmStatic @BindingAdapter("avatarUrl")
        fun loadImage(imageView: ImageView, url: String) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}