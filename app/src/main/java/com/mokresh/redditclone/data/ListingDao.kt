package com.mokresh.redditclone.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mokresh.redditclone.models.Children
import io.reactivex.Completable


@Dao
interface ListingDao {

    // get the list of topics from local DB and order ir by most upVotes
    @Query("SELECT * FROM listing ORDER BY upVotes DESC")
    fun getListing(): LiveData<List<Children>>

    // get topic by Id to use in details
    @Query("SELECT * FROM listing WHERE listingId ==:listingId")
    fun getListingById(listingId: Int): LiveData<Children>


    // insert all the topics that will come from API in local
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllListing(list: List<Children>): Completable

    // insert topic when the user will add topic manually
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListing(children: Children): Completable


    // delete all the listing table from local
    @Query("DELETE FROM listing")
    fun deleteListing(): Completable

    // increase up votes by 1
    @Query("UPDATE listing SET upVotes = upVotes+1 WHERE listingId = :listingId")
    fun increaseUpVotes(listingId: Int): Completable

    // decrease down votes by 1
    @Query("UPDATE listing SET downVotes = downVotes-1 WHERE listingId = :listingId")
    fun decreaseDownVotes(listingId: Int): Completable

}