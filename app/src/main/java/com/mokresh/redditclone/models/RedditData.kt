package com.mokresh.redditclone.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import java.util.*

data class RedditData(
    @ColumnInfo(name = "banner_img") @SerializedName("banner_img")
    val bannerImg: String? = null,

    @ColumnInfo(name = "display_name") @SerializedName("display_name")
    val displayName: String? = null,


    @ColumnInfo(name = "icon_img") @SerializedName("icon_img")
    val iconImg: String? = null,


    @ColumnInfo(name = "description") @SerializedName("description")
    val description: String? = null,


    @ColumnInfo(name = "subscribers") @SerializedName("subscribers")
    val subscribers: Int? = null,


    @ColumnInfo(name = "public_description") @SerializedName("public_description")
    val publicDescription: String? = null,

    // due to lack of up vote and down vote information , generate random number for upvote and downvote
    @ColumnInfo(name = "upVotes") val upVotes: Int = Random().nextInt(100) + 40,
    @ColumnInfo(name = "downVotes") val downVotes: Int = Random().nextInt(40) + 20

)