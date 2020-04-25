package com.mokresh.redditclone.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


// make the children as new entity in room DB
@Entity(
    tableName = "listing"
)
data class Children(

    // generate auto Id for each row
    @PrimaryKey(autoGenerate = true)
    val listingId: Int?,
    @Embedded @SerializedName("data")
    val data: RedditData?
) {
    // to add new row to the table when the user add new topic
    constructor(description: String) : this(
        null, RedditData(
            null, "New Topic", null
            , description, 0, description, 0, 0
        )
    )
}
