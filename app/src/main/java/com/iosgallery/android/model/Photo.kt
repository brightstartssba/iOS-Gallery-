package com.iosgallery.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Photo(
    val id: Long,
    val name: String,
    val path: String,
    val dateTaken: Date
) : Parcelable