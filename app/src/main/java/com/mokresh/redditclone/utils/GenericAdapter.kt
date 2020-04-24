package com.mokresh.redditclone.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mokresh.redditclone.R
import com.mokresh.redditclone.ui.ListingViewHolder


class GenericAdapter<T>(private val context: Context, private val items: List<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when {
            else -> ListingViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.listing_item,
                    parent,
                    false
                )
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