package com.mokresh.redditclone.app

import com.mokresh.redditclone.models.Children
import com.mokresh.redditclone.models.Listing
import com.mokresh.redditclone.models.RedditResponseBody
import com.mokresh.redditclone.utils.BaseSchedulers
import com.mokresh.redditclone.utils.Constants
import com.mokresh.redditclone.utils.Constants.QUERY_LIMIT
import com.mokresh.redditclone.utils.Constants.QUERY_PAGINATE_AFTER
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    fun listing(): Single<List<Children>>

    class Network(
        private val retrofit: Retrofit,
        private val schedulers: BaseSchedulers
    ) : ApiServices {

        override fun listing(): Single<List<Children>> {

            return retrofit.create(NetworkCalls::class.java)
                .getRedditListing()
                .subscribeOn(schedulers.io())
                .map { it.listing.children }
        }


        interface NetworkCalls {

            @GET(Constants.QUERY_REDDITS)
            fun getRedditListing(
                @Query(QUERY_PAGINATE_AFTER) nextPage: String = "",
                @Query(QUERY_LIMIT) limit: Int = Constants.DEFAULT_LIMIT
            ): Single<RedditResponseBody<Listing<List<Children>>>>

        }

    }
}