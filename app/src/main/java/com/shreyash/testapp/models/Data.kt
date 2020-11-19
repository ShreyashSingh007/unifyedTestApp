package com.shreyash.testapp.models


import com.google.gson.annotations.SerializedName
/**
 * This is the data model for the Main recyclerView to display a Category heading and its list of data for inner
 * recycler view
 * @author Shreyash Singh
 * @version 1.0
 */
data class Data(
    @SerializedName("heading")
    val heading: String,
    @SerializedName("listData")
    val listData: List<InnerData>
)