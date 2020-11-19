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
import com.shreyash.testapp.adapters.tabTwo.TabTwoAdapter
import com.shreyash.testapp.helpers.Resource
import com.shreyash.testapp.helpers.Utility
import com.shreyash.testapp.interfaces.TabNavigator
import com.shreyash.testapp.models.InnerData
import com.shreyash.testapp.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_tab_two.*


class TabTwoFragment : Fragment(), TabNavigator {

    lateinit var tabTwoAdapter: TabTwoAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpLiveDataObservers()
    }

    /**
     * FUnction to setup up live data observers
     */
    private fun setUpLiveDataObservers() {
        viewModel.data.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    tabTwoAdapter.differ.submitList(it.data)
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
        lm.orientation = LinearLayoutManager.VERTICAL
        tabTwoAdapter = TabTwoAdapter(this)
        rv_tabTwoFragment.apply {
            layoutManager = lm
            adapter = tabTwoAdapter
        }
    }

    /**
     * This is the overridden method for @interface @TabNavigator
     * Used to navigate to the Detailed fragment when user clicks on RecyclerView ViewHolder
     * Also Passes the clicked ViewHolder data to Activity's ViewModel for Fragment to Fragment Communication
     */
    override fun onClicked(data: InnerData) {
        viewModel.clickedData = data
        Utility.navigateFragment(
                requireActivity().supportFragmentManager,
                R.id.mainFragContainer,
                DetailedFragment(),
                "detailedFrag",
                true
        )
    }

}