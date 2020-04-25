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

        override fun getListing(): LiveData<List<Children>> {
            return dao.getListing()
        }

        override fun getListingById(listingId: Int): LiveData<Children> {
            return dao.getListingById(listingId)
        }

        override fun upVote(listingId: Int) {
            dao.increaseUpVotes(listingId).subscribeOn(Schedulers.io()).subscribe()

        }

        override fun downVote(listingId: Int) {
            dao.decreaseDownVotes(listingId).subscribeOn(Schedulers.io()).subscribe()
        }


        override fun cleared() {
            compositeDisposable.clear()
        }

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