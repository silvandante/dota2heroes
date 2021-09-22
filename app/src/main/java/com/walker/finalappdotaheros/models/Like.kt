package com.walker.finalappdotaheros.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "like_table")
@Parcelize
data class Like(
        var heroId: Int,
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
) : Parcelable