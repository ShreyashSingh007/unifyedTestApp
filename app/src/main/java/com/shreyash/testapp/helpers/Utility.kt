package com.shreyash.testapp.helpers

import android.content.Context
import android.view.Gravity
import androidx.annotation.RawRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.transition.Fade
import androidx.transition.Slide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Utility {

    /**
     * This is an utility class for defining the fragment transition animations , Navigation and Reading Raw JSON Data .
     * @author Shreyash Singh
     * @version 1.0
     */


    /**
     * Method reads the data from the json file(present in raw folder and returns it as a Kotlin Object
     * @param context -> Application context for accessing the resources
     * @param rawResId -> ".json" file ID's present in resources/raw
     *
     * @return Kotlin Object for the json file
     */
    inline fun <reified T> readRawJson(context: Context, @RawRes rawResId: Int): T {
        context.resources.openRawResource(rawResId).bufferedReader().use {
            return Gson().fromJson(it, object : TypeToken<T>() {}.type)
        }
    }

    /**
     * Used for applying Slide In & Slide Out Fragment Animations
     *
     * @return Fragment Object with applied animations
     */

    private fun Fragment.applyTransaction(): Fragment {

        val startAnimation = Slide(Gravity.END)
        startAnimation.duration = 400L

        val endAnimation = Fade()
        endAnimation.duration = 200L

        this.enterTransition = startAnimation
        this.exitTransition = endAnimation

        return this
    }

    /**
     *Fragment Navigation
     *
     * @param fragManager -> fragment Manager to add/remove fragments
     * @param containerID -> ID of fragment Container
     * @param fragment -> Object of the Fragment to navigate to
     * @param tag -> tag for fragments, used in back stack
     * @param addToBackStack -> Control parameter for adding or removing fragment from back stack
     */

    fun navigateFragment(
            fragManager: FragmentManager,
            containerID: Int,
            fragment: Fragment,
            tag: String,
            addToBackStack: Boolean = true
    ) {

        val transaction =
                fragManager.beginTransaction().replace(containerID, fragment.applyTransaction())
        if (addToBackStack)
            transaction.addToBackStack(tag)

        transaction.commit()
    }

}