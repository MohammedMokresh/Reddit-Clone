package com.mokresh.redditclone.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mokresh.redditclone.models.Children
import io.reactivex.Completable


@Dao
interface ListingDao {

    @Query("SELECT * FROM listing ORDER BY upVotes DESC")
    fun getListing(): LiveData<List<Children>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListing(list: List<Children>): Completable

    @Query("DELETE FROM listing")
    fun deleteListing(): Completable

}