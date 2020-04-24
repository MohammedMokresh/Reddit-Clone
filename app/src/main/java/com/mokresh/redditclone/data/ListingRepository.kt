package com.mokresh.redditclone.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.mokresh.redditclone.app.ApiServices
import com.mokresh.redditclone.models.Children
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

interface ListingRepository {

    fun cleared()
    fun insertListingInLocal()
    fun getListing(): LiveData<List<Children>>
    fun getListingById(listingId: Int): LiveData<Children>


    open class ListingRepositoryImpl(
        private val service: ApiServices,
        private val dao: ListingDao
    ) : ListingRepository {

        private val compositeDisposable: CompositeDisposable = CompositeDisposable()

        override fun insertListingInLocal() {
            getListingFromAPI()
                .subscribeOn(Schedulers.io())

                .flatMapCompletable {
                    dao.deleteListing()
                        .andThen(insertListing(it))
                }

                .subscribeBy(
                    onError = {
                        Log.d("exception", it.message)
                    }
                ).addTo(compositeDisposable)
        }

        override fun getListing(): LiveData<List<Children>> {
            return dao.getListing()
        }

        override fun getListingById(listingId: Int): LiveData<Children> {
            return dao.getListingById(listingId)
        }


        override fun cleared() {
            compositeDisposable.clear()
        }


        private fun insertListing(list: List<Children>): Completable {
            return dao.insertListing(list.map { it })
        }

        private fun getListingFromAPI(): Single<List<Children>> =
            service.listing().map { it }

    }


}