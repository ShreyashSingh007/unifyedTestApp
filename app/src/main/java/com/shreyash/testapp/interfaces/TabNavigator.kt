package com.shreyash.testapp.interfaces

import com.shreyash.testapp.models.InnerData

/**
 * This is the interface implemented by fragments to allow their RecyclerViews to navigate to Detailed Fragment
 * @author Shreyash Singh
 * @version 1.0
 */
interface TabNavigator {
    /**
     *@param data -> Represents the ViewHolder's data that is passed to the view model for fragment to fragment
     * communication
     */
    fun onClicked(data: InnerData)
}