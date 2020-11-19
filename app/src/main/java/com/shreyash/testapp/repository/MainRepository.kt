package com.shreyash.testapp.repository

import android.content.Context
import androidx.annotation.RawRes
import com.shreyash.testapp.helpers.Utility
import com.shreyash.testapp.models.GenericDataModel

/**
 * This is the Repository class, responsible for providing data across the application
 * @author Shreyash Singh
 * @version 1.0
 */
class MainRepository {

    /**
     * Method reads the data from the json file(present in raw folder and returns it as a Kotlin Object
     * @param context -> Application context for accessing the resources
     * @param id -> ".json" file ID's present in resources/raw
     */
    fun getData(context: Context, @RawRes id: Int): GenericDataModel {
        return Utility.readRawJson(context, id)
    }
}