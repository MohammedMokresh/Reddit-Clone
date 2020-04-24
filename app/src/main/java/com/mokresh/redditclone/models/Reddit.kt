package com.mokresh.redditclone.models

import com.google.gson.annotations.SerializedName

data class Reddit(

    @SerializedName("kind")
    private val kind: String? = null,
    @SerializedName("data") val listing: Listing? = null

)
