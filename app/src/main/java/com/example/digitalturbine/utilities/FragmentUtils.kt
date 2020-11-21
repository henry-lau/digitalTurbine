package com.example.digitalturbine.utilities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.digitalturbine.R

object FragmentUtils {

    fun showFragment(
        activity: FragmentActivity,
        fragment: Fragment,
        tag: String?
    ) {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return
        }
        val transaction: FragmentTransaction =
            activity.getSupportFragmentManager().beginTransaction()
        transaction.replace(R.id.fragment_container, fragment, tag)
        transaction.commitAllowingStateLoss()
    }

    fun addFragment(
        activity: FragmentActivity,
        newfragment: Fragment,
        oldfragment: Fragment,
        tag: String?
    ) {
        if (newfragment.javaClass.canonicalName.equals(oldfragment.javaClass.canonicalName)) {
            return
        }
        if (activity.isFinishing() || activity.isDestroyed()) {
            return
        }
        val state: Fragment.SavedState? =
            activity.getSupportFragmentManager().saveFragmentInstanceState(oldfragment)
        newfragment.setInitialSavedState(state)
        val transaction: FragmentTransaction =
            activity.getSupportFragmentManager().beginTransaction()
        transaction.replace(R.id.fragment_container, newfragment, tag).addToBackStack(tag)
        transaction.commitAllowingStateLoss()
    }
}