package com.mokresh.redditclone.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "listing"
)
data class Children(
    @PrimaryKey(autoGenerate = true)
    val listingId: Int?,
    @Embedded @SerializedName("data")
    val data: RedditData?
) {
    constructor(description: String) : this(
        null, RedditData(
            null, "New Topic", null
            , description, null, description, 0, 0
        )
    )
}
