package com.iuriirodyk.squarerepos.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.iuriirodyk.squarerepos.R
import com.iuriirodyk.squarerepos.ui.activity.MainActivity
import com.iuriirodyk.squarerepos.ui.adapter.RepositoryListAdapter
import com.iuriirodyk.squarerepos.ui.fragment.base.BaseFragment
import com.iuriirodyk.squarerepos.ui.fragment.extension.inTransaction
import com.iuriirodyk.squarerepos.viewmodel.*
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_repository_list.*
import kotlinx.android.synthetic.main.fragment_repository_list.view.*
import javax.inject.Inject

class RepositoryListFragment : BaseFragment(), RepositoryListAdapter.OnItemClickListener {

    override lateinit var title: String

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RepositoryListViewModel

    private val stateObserver = Observer<State<RepositoryListViewModel.SquareRepo>> { state ->
        state.let {
            when (state) {
                is DefaultState -> {
                    (rv_repository_list.adapter as RepositoryListAdapter).items = it.data
                    (rv_repository_list.adapter as RepositoryListAdapter).notifyDataSetChanged()
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
        title = getString(R.string.app_name)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepositoryListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_repository_list, container, false)

        view.rv_repository_list.adapter = RepositoryListAdapter(this, emptyList())

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.liveDataState.removeObserver(stateObserver)
    }

    override fun onItemClick(item: RepositoryListViewModel.SquareRepo) {
        activity?.supportFragmentManager?.inTransaction {
            replace(R.id.main_container, RepositoryDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(RepositoryDetailFragment.ARG_REPOSITORY_KEY, item)
                }
            }).addToBackStack(null)
        }
    }

    private fun observeViewModel() {
        viewModel.liveDataState.observe(viewLifecycleOwner, stateObserver)
    }
}