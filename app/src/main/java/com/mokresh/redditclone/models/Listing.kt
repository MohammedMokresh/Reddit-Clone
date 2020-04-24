package com.mokresh.redditclone.models

import com.google.gson.annotations.SerializedName


data class Listing<T>(
    @SerializedName("children") val children: T
)