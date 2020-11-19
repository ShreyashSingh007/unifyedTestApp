package com.shreyash.testapp.models


import com.google.gson.annotations.SerializedName

/**
 * This is the data model for the Inner recyclerView to display tile and subtitle,holds other metadata for detailed view
 * recycler view
 * @author Shreyash Singh
 * @version 1.0
 */
data class InnerData(
        @SerializedName("description")
        val description: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("subtitle")
        val subtitle: String,
        @SerializedName("title")
        val title: String
)