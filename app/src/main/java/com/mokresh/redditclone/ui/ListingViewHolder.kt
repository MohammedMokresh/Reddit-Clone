package com.mokresh.redditclone.ui

import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.mokresh.redditclone.R
import com.mokresh.redditclone.databinding.ListingItemBinding
import com.mokresh.redditclone.models.Children
import com.mokresh.redditclone.utils.FragmentSwitcher
import com.mokresh.redditclone.utils.GenericAdapter
import com.mokresh.redditclone.utils.ImageUtil

class ListingViewHolder(
    private val itemBinding: ListingItemBinding,
    private val fragmentManager: FragmentManager,
    private val listener: ListingItemButtonsListener
) :
    RecyclerView.ViewHolder(itemBinding.root), GenericAdapter.Binder<Children> {


    override fun bind(data: Children, position: Int) {

        val redditData = data.data

        if (redditData != null) {
            val image = redditData.iconImg
            ImageUtil.renderImage(
                image,
                itemBinding.itemIcon,
                R.drawable.ic_launcher_background,
                itemBinding.root.context
            )
            itemBinding.itemName.text = redditData.displayName.orEmpty()
            itemBinding.itemDescription.text = redditData.publicDescription.orEmpty()

            itemBinding.upVoteButton.text = redditData.upVotes.toString()
            itemBinding.downVoteButton.text = redditData.downVotes.toString()

            itemBinding.downVoteButton.setOnClickListener {
                if (redditData.downVotes > 0)
                    listener.downVoteButtonClickListener(
                        data.listingId
                    )
            }
            itemBinding.upVoteButton.setOnClickListener { listener.upVoteButtonClickListener(data.listingId) }

            itemBinding.root.setOnClickListener {
                FragmentSwitcher.addFragment(
                    fragmentManager
                    , R.id.details_FrameLayout, DetailsFragment.newInstance(data.listingId)
                )
            }
        }
    }

    interface ListingItemButtonsListener {
        fun upVoteButtonClickListener(listingId: Int)
        fun downVoteButtonClickListener(listingId: Int)
    }

}