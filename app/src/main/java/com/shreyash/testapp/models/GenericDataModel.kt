package com.shreyash.testapp.models


import com.google.gson.annotations.SerializedName

/**
 * This is the data model Stores the immediate result after parsing the json file
 * @author Shreyash Singh
 * @version 1.0
 */

data class GenericDataModel(
        @SerializedName("data")
        val data: List<Data>
)