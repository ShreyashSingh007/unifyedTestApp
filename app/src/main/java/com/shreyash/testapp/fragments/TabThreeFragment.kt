package com.shreyash.testapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shreyash.testapp.R
import com.shreyash.testapp.adapters.tabThree.TabThreeAdapter
import com.shreyash.testapp.helpers.Resource
import com.shreyash.testapp.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_tab_three.*

class TabThreeFragment : Fragment() {

    private lateinit var tabThreeAdapter: TabThreeAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpLiveDataObservers()
        setUpClickListeners()
    }

    /**
     * OnCLickListeners of buttons used for Adding a Number,Computing 2nd largest Number and Clear
     */
    private fun setUpClickListeners() {
        btn_addTabThree.setOnClickListener {
            viewModel.addNumber(tv_enterNumberTabThree.editText?.text.toString())
        }

        btn_doneTabThree.setOnClickListener {
            viewModel.computeData()
        }

        btb_clearTabThree.setOnClickListener {
            doneGrp.visibility = View.GONE
            viewModel.clear()
        }
    }

    /**
     * Function to setup up live data observers
     */
    private fun setUpLiveDataObservers() {
        viewModel.tabThreeData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    tabThreeAdapter.differ.submitList(it.data)
                }
                else -> {
                }
            }
        })


        viewModel.tabThreeResult.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    doneGrp.visibility = View.VISIBLE
                    tv_secondLargestNumber.editText?.setText(it.data?.secondLargestNumber)
                    tv_addNumber.editText?.setText(it.data?.allNumbers)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "" + it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {

                }
            }
        })
    }

    /**
     * Function to setup recycler view
     */
    private fun setUpRecyclerView() {
        val lm = LinearLayoutManager(requireContext())
        lm.orientation = LinearLayoutManager.HORIZONTAL
        tabThreeAdapter = TabThreeAdapter()
        rv_tabThree.apply {
            layoutManager = lm
            adapter = tabThreeAdapter
        }
    }
}