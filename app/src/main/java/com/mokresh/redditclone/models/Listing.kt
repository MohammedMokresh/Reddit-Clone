package com.mokresh.redditclone.models

import com.google.gson.annotations.SerializedName


data class Listing(
    @SerializedName("children") val children: List<Children>? = null
)