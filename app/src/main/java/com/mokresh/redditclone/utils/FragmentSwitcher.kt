package com.mokresh.redditclone.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.mokresh.redditclone.R


object FragmentSwitcher {

    fun addFragment(
        fragmentManager: FragmentManager,
        @IdRes containerID: Int,
        newFragment: Fragment?
    ) {
        val fragmentTransaction =
            fragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in_from_right, R.anim.slide_out_from_left,
            R.anim.slide_in_from_left, R.anim.slide_out_from_right
        )

        fragmentTransaction.add(containerID, newFragment!!)
        fragmentTransaction.addToBackStack("add_fragment")
        fragmentTransaction.commit()
    }

    fun closeFragment(fragmentManager: FragmentManager, fragment: Fragment) {
        val trans = fragmentManager.beginTransaction()
        trans.setCustomAnimations(
            R.anim.slide_in_from_right,
            R.anim.slide_out_from_right,
            R.anim.slide_in_from_left,
            R.anim.slide_out_from_right
        )
        trans.remove(fragment)
        trans.commit()
        fragmentManager.popBackStack()
    }


}