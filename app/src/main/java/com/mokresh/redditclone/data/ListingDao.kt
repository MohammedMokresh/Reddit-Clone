package com.mokresh.redditclone.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mokresh.redditclone.models.Children
import io.reactivex.Completable


@Dao
interface ListingDao {

    @Query("SELECT * FROM listing ORDER BY upVotes DESC")
    fun getListing(): LiveData<List<Children>>

    @Query("SELECT * FROM listing WHERE listingId ==:listingId")
    fun getListingById(listingId: Int): LiveData<Children>


    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllListing(list: List<Children>): Completable

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListing(children: Children): Completable

    @Query("DELETE FROM listing")
    fun deleteListing(): Completable


    @Query("UPDATE listing SET upVotes = upVotes+1 WHERE listingId = :listingId")
    fun increaseUpVotes(listingId: Int): Completable


    @Query("UPDATE listing SET downVotes = downVotes-1 WHERE listingId = :listingId")
    fun decreaseDownVotes(listingId: Int): Completable

}