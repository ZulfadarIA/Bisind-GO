package com.zulfadar.bisindgo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vocabulary(
    val kosakata: String,
    val photo: Int
) : Parcelable
