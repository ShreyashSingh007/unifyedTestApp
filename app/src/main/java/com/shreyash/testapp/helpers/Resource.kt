package com.shreyash.testapp.helpers

/**
 * This is a state management Wrapper class
 * @author Shreyash Singh
 * @version 1.0
 */
sealed class Resource<T>(
        val data: T? = null,
        val message: String? = null
) {
    class Success<T>(data: T, message: String? = null) : Resource<T>(data, message)
    class Error<T>(data: T? = null, message: String) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
    class NavigateForward<T>(data: T? = null) : Resource<T>(data)
}