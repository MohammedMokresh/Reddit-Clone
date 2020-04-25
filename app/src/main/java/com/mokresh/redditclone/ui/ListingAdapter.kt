package com.mokresh.redditclone.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mokresh.redditclone.R
import com.mokresh.redditclone.databinding.ListingItemBinding
import com.mokresh.redditclone.models.Children
import com.mokresh.redditclone.utils.FragmentSwitcher
import com.mokresh.redditclone.utils.ImageUtil

class ListingAdapter(
    private val fragmentManager: FragmentManager,
    private val listener: ListingItemButtonsListener
) : RecyclerView.Adapter<ListingAdapter.ViewHolder>() {

    private val childrenList = ArrayList<Children>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListingItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.listing_item, parent, false)
        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = childrenList[position]
        val redditData = data.data

        if (redditData != null) {
            val image = redditData.iconImg
            ImageUtil.renderImage(
                image,
                holder.binding.itemIcon,
                R.drawable.ic_launcher_background,
                holder.binding.root.context
            )
            holder.binding.itemName.text = redditData.displayName.orEmpty()
            holder.binding.itemDescription.text = redditData.publicDescription.orEmpty()

            holder.binding.upVoteButton.text = redditData.upVotes.toString()
            holder.binding.downVoteButton.text = redditData.downVotes.toString()

            if (data.listingId != null) {
                holder.binding.downVoteButton.setOnClickListener {
                    if (redditData.downVotes > 0) {
                        listener.downVoteButtonClickListener(
                            data.listingId
                            , position
                        )
                    }
                }
                holder.binding.upVoteButton.setOnClickListener {
                    listener.upVoteButtonClickListener(
                        data.listingId
                        , position
                    )
                }

                holder.binding.root.setOnClickListener {
                    FragmentSwitcher.addFragment(
                        fragmentManager
                        , R.id.details_FrameLayout, DetailsFragment.newInstance(data.listingId)
                    )
                }
            }

        }
    }

    // to refresh the recyclerview data
    fun setData(newChildren: List<Children>) {
        val diffCallback = ChildrenDiffCallback(childrenList, newChildren)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        childrenList.clear()
        childrenList.addAll(newChildren)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun getItemCount(): Int {
        return childrenList.size
    }

    class ViewHolder(itemBinding: ListingItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ListingItemBinding = itemBinding
    }

    // create this listener to use in inside the activity when the button inside the recyclerview item clicked
    interface ListingItemButtonsListener {
        fun upVoteButtonClickListener(listingId: Int, position: Int)
        fun downVoteButtonClickListener(listingId: Int, position: Int)
    }

}