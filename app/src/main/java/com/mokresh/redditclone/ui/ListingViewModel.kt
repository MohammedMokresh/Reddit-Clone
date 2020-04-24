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

    fun insertListingInLocal() {
        return listingRepository.insertListingInLocal()
    }

    override fun onCleared() {
        listingRepository.cleared()
    }

}