package com.mokresh.redditclone.ui

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.mokresh.redditclone.models.Children

class ChildrenDiffCallback(
    private val oldList: List<Children>,
    private val newList: List<Children>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].listingId === newList.get(newItemPosition).listingId
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}