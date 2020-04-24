package com.mokresh.redditclone.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.mokresh.redditclone.R
import com.mokresh.redditclone.ui.ListingViewHolder


class GenericAdapter<T>(
    private val context: Context,
    private val items: List<T>,
    private val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private lateinit var listener: ListingViewHolder.ListingItemButtonsListener
    fun setListingClickListener(listener: ListingViewHolder.ListingItemButtonsListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when {
            else -> ListingViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.listing_item,
                    parent,
                    false
                )
                , fragmentManager,listener
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position < items.size)
            (holder as Binder<T>).bind(items[position], position)

    }

    override fun getItemCount(): Int {
        return items.size
    }


    interface Binder<T> {
        fun bind(data: T, position: Int)

    }
}