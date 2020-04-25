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

    fun upVote(listingId: Int)
    fun downVote(listingId: Int)
    fun insertListing(children: Children)

    open class ListingRepositoryImpl(
        private val service: ApiServices,
        private val dao: ListingDao
    ) : ListingRepository {

        private val compositeDisposable: CompositeDisposable = CompositeDisposable()

        // get the data from API and insert it in local DB
        override fun insertListingInLocal() {
            getListingFromAPI()
                .subscribeOn(Schedulers.io())

                .flatMapCompletable {
                    dao.deleteListing()
                        .andThen(insertAllListing(it))
                }

                .subscribeBy(
                    onError = {
                        Log.d("exception", it.message)
                    }
                ).addTo(compositeDisposable)
        }

        // get the data from single source by getting it from Room DB
        override fun getListing(): LiveData<List<Children>> {
            return dao.getListing()
        }

        // get data by id
        override fun getListingById(listingId: Int): LiveData<Children> {
            return dao.getListingById(listingId)
        }

        // increase the upvotes in local DB
        override fun upVote(listingId: Int) {
            dao.increaseUpVotes(listingId).subscribeOn(Schedulers.io()).subscribe()

        }
        // decrease the downv votes in local db
        override fun downVote(listingId: Int) {
            dao.decreaseDownVotes(listingId).subscribeOn(Schedulers.io()).subscribe()
        }

        // clear the  compositeDisposable after the view model cleared
        override fun cleared() {
            compositeDisposable.clear()
        }
        // to insert data when user add new topic
        override fun insertListing(children: Children) {
            dao.insertListing(children).subscribeOn(Schedulers.io()).subscribe()
        }


        private fun insertAllListing(list: List<Children>): Completable {
            return dao.insertAllListing(list.map { it })
        }

        private fun getListingFromAPI(): Single<List<Children>> =
            service.listing().map { it }

    }


}