package com.mokresh.redditclone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mokresh.redditclone.data.ListingRepository
import com.mokresh.redditclone.models.Children

class ListingViewModel(
    private val listingRepository: ListingRepository
) : ViewModel() {


    fun getListing(): LiveData<List<Children>> {
        return listingRepository.getListing()
    }

    fun getListingById(listingId: Int): LiveData<Children> {
        return listingRepository.getListingById(listingId)
    }

    fun upVote(listingId: Int) {
        listingRepository.upVote(listingId)
    }

    fun downVote(listingId: Int) {
        listingRepository.downVote(listingId)
    }

    fun insertListingInLocal() {
        return listingRepository.insertListingInLocal()
    }

    override fun onCleared() {
        listingRepository.cleared()
    }

}