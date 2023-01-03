package com.example.googletasksclone

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    var isCompleted: Boolean,
    var text: String,
    var isFavourite: Boolean
) : Parcelable