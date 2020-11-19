package com.shreyash.testapp.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shreyash.testapp.repository.MainRepository

/**
 * This is the ViewModelFactory used to initialise ViewModel with custom Constructor parameters
 * @author Shreyash Singh
 * @version 1.0
 */
class MainViewModelFactory(private val mainRepository: MainRepository, val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository, context) as T
    }
}