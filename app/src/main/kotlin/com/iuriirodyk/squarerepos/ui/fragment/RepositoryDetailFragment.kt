package com.iuriirodyk.squarerepos.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.iuriirodyk.squarerepos.BR
import com.iuriirodyk.squarerepos.R
import com.iuriirodyk.squarerepos.databinding.FragmentRepositoryDetailBinding
import com.iuriirodyk.squarerepos.ui.fragment.base.BaseFragment
import com.iuriirodyk.squarerepos.ui.activity.MainActivity
import com.iuriirodyk.squarerepos.ui.adapter.RepositoryStargazerListAdapter
import com.iuriirodyk.squarerepos.viewmodel.*
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_repository_detail.*
import kotlinx.android.synthetic.main.fragment_repository_detail.view.*
import javax.inject.Inject

class RepositoryDetailFragment : BaseFragment() {

    companion object {
        const val ARG_REPOSITORY_KEY = "repository_key"
    }

    override lateinit var title: String

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentRepositoryDetailBinding
    private lateinit var viewModel: RepositoryDetailViewModel

    private var squareRepo: RepositoryListViewModel.SquareRepo? = null

    private val stateObserver = Observer<State<RepositoryDetailViewModel.Stargazer>> { state ->
        state.let {
            when (state) {
                is DefaultState -> {
                    (rv_stargazers_list.adapter as RepositoryStargazerListAdapter).items = it.data
                    (rv_stargazers_list.adapter as RepositoryStargazerListAdapter).notifyDataSetChanged()
                    hideProgressDialog()
                }
                is LoadingState -> {
                    showProgressDialog("Loading...")
                }
                is ErrorState -> {
                    hideProgressDialog()
                    Snackbar.make(view!!, (it as ErrorState).errorMessage, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        arguments?.let {
            if (it.containsKey(ARG_REPOSITORY_KEY)) {
                squareRepo = it.getParcelable(ARG_REPOSITORY_KEY)
                squareRepo?.let { repo -> title = repo.name }
            }
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepositoryDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_repository_detail, container, false)
        binding.setVariable(BR.squareRepo, squareRepo)
        binding.setVariable(BR.fragment, this)
        binding.executePendingBindings()

        binding.root.rv_stargazers_list.adapter = RepositoryStargazerListAdapter(emptyList())

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(viewModel.liveDataState.value?.data.isNullOrEmpty()){
            squareRepo?.let {
                viewModel.getRepositoryDetails(it.name)
            }
        }

        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.liveDataState.removeObserver(stateObserver)
    }

    fun bookmarkClick(){
        squareRepo?.let {
            if(it.isBookmarked) {
                viewModel.removeBookmark(it.name)
                binding.root.fab_bookmark.setImageResource(R.drawable.ic_bookmark_border)
            }
            else {
                viewModel.addBookmark(it.name)
                binding.root.fab_bookmark.setImageResource(R.drawable.ic_bookmark)
            }
            it.isBookmarked = !it.isBookmarked
        }
    }

    private fun observeViewModel() {
        viewModel.liveDataState.observe(viewLifecycleOwner, stateObserver)
    }
}