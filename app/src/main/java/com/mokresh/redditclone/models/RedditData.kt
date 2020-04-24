package com.mokresh.redditclone.models

import com.google.gson.annotations.SerializedName

data class RedditData(
        @SerializedName("banner_img")
        val bannerImg: String? = null,
        @SerializedName("id")
        val id: String? = null,

        @SerializedName("display_name")
        val displayName: String? = null,


        @SerializedName("icon_img")
        val iconImg: String? = null,


        @SerializedName("description")
        val description: String? = null,


        @SerializedName("subscribers")
        val subscribers: Int? = null,


        @SerializedName("key_color")
        val keyColor: String? = null,

        @SerializedName("public_description")
        val publicDescription: String? = null
)