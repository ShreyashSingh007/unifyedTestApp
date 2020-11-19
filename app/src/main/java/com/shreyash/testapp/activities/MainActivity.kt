package com.shreyash.testapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shreyash.testapp.R
import com.shreyash.testapp.fragments.HomeFragment
import com.shreyash.testapp.helpers.Utility
import com.shreyash.testapp.repository.MainRepository
import com.shreyash.testapp.viewModels.MainViewModel
import com.shreyash.testapp.viewModels.MainViewModelFactory

/**
 * This is main activity, Entry point of the application
 * @author Shreyash Singh
 * @version 1.0
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Adding Fragment
        Utility.navigateFragment(supportFragmentManager, R.id.mainFragContainer, HomeFragment(), "homeFrag", false)


        //Adding ViewModels,So that all fragments can be accessed
        val factory = MainViewModelFactory(MainRepository(), this.applicationContext)
        val viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }
}