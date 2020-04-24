package com.mokresh.redditclone.models

import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("data")
    val data: RedditData?
)
