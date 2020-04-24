package com.mokresh.redditclone.models

import com.google.gson.annotations.SerializedName

data class RedditResponseBody<T>(
    @SerializedName("data") val listing: T
)
