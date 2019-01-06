package com.iuriirodyk.squarerepos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.iuriirodyk.squarerepos.BR
import com.iuriirodyk.squarerepos.R
import com.iuriirodyk.squarerepos.databinding.RepositoryListItemBinding
import com.iuriirodyk.squarerepos.viewmodel.RepositoryListViewModel

class RepositoryListAdapter(private val listener: OnItemClickListener,
                            var items: List<RepositoryListViewModel.SquareRepo>)
    : RecyclerView.Adapter<RepositoryListAdapter.RepoViewHolder>(){

    interface OnItemClickListener {
        fun onItemClick(item: RepositoryListViewModel.SquareRepo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RepositoryListItemBinding>(
            inflater, R.layout.repository_list_item, parent, false)
        binding.listener = listener
        return RepoViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) = holder.bind(items[position])

    inner class RepoViewHolder(private val binding: ViewDataBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepositoryListViewModel.SquareRepo) {
            binding.setVariable(BR.squareRepo, item)
            binding.executePendingBindings()
        }
    }
}