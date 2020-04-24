package com.mokresh.redditclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mokresh.redditclone.databinding.ActivityMainBinding
import com.mokresh.redditclone.ui.ListingViewHolder
import com.mokresh.redditclone.ui.ListingViewModel
import com.mokresh.redditclone.utils.GenericAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ListingViewHolder.ListingItemButtonsListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ListingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initAdapter()
        binding.topicsSwipeRefreshLayout.setOnRefreshListener { viewModel.insertListingInLocal() }
    }

    private fun initAdapter() {
        binding.topicsSwipeRefreshLayout.isRefreshing = true


        viewModel.insertListingInLocal()
        viewModel.getListing().observe(this, Observer {
            binding.topicsRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                val genericAdapter = GenericAdapter(applicationContext, it, supportFragmentManager)
                genericAdapter.setListingClickListener(this@MainActivity)

                adapter = genericAdapter
                binding.topicsSwipeRefreshLayout.isRefreshing = false
            }
        })

    }

    override fun upVoteButtonClickListener(listingId: Int) {
        viewModel.upVote(listingId)
    }

    override fun downVoteButtonClickListener(listingId: Int) {
        viewModel.downVote(listingId)
    }

}
