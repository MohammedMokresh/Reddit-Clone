package com.mokresh.redditclone.data.mockWebServer

import com.mokresh.redditclone.data.getJson
import com.mokresh.redditclone.app.ApiServices
import com.mokresh.redditclone.utils.Constants
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class DispatcherMockWebServer : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        val requestUrl = request.requestUrl

        return when (requestUrl?.encodedPath) {
            Constants.QUERY_REDDITS -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        getJson(
                            "listings/listing_page.json",
                            ApiServices::class
                        )
                    )
            }
            else -> MockResponse().setResponseCode(404)
        }


    }

}