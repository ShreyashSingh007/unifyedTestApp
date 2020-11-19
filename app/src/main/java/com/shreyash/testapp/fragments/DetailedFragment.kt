package com.shreyash.testapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.shreyash.testapp.R
import com.shreyash.testapp.models.InnerData
import com.shreyash.testapp.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_detailed.*

/**
 * This is detailed fragment page which appears upon clicking any item TabOne or TabTwo Fragments
 * @author Shreyash Singh
 * @version 1.0
 */
class DetailedFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    //Holds a reference to the viewHolder data for fragment to fragment communication
    private lateinit var clickedData: InnerData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        clickedData = viewModel.clickedData
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpData()
    }

    /**
     * This function tries to Load the image from url and sets the title and subtitle and description
     */
    private fun setUpData() {
        iv_detailFragment.load(clickedData.link)
        tv_titleDetailedFragment.text = clickedData.title
        tv_subtitleDetailedFragment.text = clickedData.subtitle
        tv_abstractDetailedFragment.text = clickedData.description
    }
}