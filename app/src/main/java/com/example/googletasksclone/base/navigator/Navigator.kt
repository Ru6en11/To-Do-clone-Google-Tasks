package com.example.googletasksclone.base.navigator

import androidx.fragment.app.Fragment
import com.example.googletasksclone.views.current.CurrentTaskFragment

interface Navigator {

    fun launch(f: Fragment)

    fun goBack()

}