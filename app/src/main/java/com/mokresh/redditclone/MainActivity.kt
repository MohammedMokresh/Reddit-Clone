package com.mokresh.redditclone

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mokresh.redditclone.databinding.ActivityMainBinding
import com.mokresh.redditclone.models.Children
import com.mokresh.redditclone.dialogs.AddTopicBottomSheetFragment
import com.mokresh.redditclone.ui.ListingAdapter
import com.mokresh.redditclone.ui.ListingViewModel
import com.mokresh.redditclone.dialogs.DialogUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),
    AddTopicBottomSheetFragment.AddTopicClickListener, ListingAdapter.ListingItemButtonsListener {

    private lateinit var binding: ActivityMainBinding

    // inject the view model using koin
    private val viewModel: ListingViewModel by viewModel()
    private var childrenList: ArrayList<Children> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initAdapter()
        binding.topicsSwipeRefreshLayout.setOnRefreshListener { viewModel.insertListingInLocal() }

        // show add new topics dialog when press the FAB button
        binding.addTopicFAB.setOnClickListener {
            DialogUtil.showAddTopicDialog(
                supportFragmentManager,
                this
            )
        }
    }

    // initialize the recyclerview with the latest data from API
    private fun initAdapter() {
        binding.topicsSwipeRefreshLayout.isRefreshing = true
        val listingAdapter = ListingAdapter(supportFragmentManager, this)
        binding.topicsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.topicsRecyclerView.adapter = listingAdapter

        viewModel.insertListingInLocal()
        viewModel.getListing().observe(this, Observer {
            binding.topicsSwipeRefreshLayout.isRefreshing = false
            childrenList= ArrayList()
            childrenList.addAll(it)
            listingAdapter.setData(childrenList)

        })

    }

    // listener to the up vote button in the recyclerview item
    override fun upVoteButtonClickListener(listingId: Int, position: Int) {
        viewModel.upVote(listingId)
    }
    // listener to the down vote button in the recyclerview item
    override fun downVoteButtonClickListener(listingId: Int, position: Int) {
        viewModel.downVote(listingId)
    }

    // listener to submit button in the add topic dialog
    override fun submitOnClick(topic: String) {
        viewModel.insertListing(Children(topic))
        Toast.makeText(applicationContext,getString(R.string.new_topic_added),Toast.LENGTH_LONG).show()
    }

}
