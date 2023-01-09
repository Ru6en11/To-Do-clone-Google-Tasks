package com.example.googletasksclone.model.task

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    var isCompleted: Boolean,
    var text: String,
    var additionalInfo: String,
    var isFavourite: Boolean
) : Parcelable