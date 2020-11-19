package com.shreyash.testapp.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shreyash.testapp.R
import com.shreyash.testapp.helpers.Resource
import com.shreyash.testapp.models.Data
import com.shreyash.testapp.models.InnerData
import com.shreyash.testapp.models.TabThreeModel
import com.shreyash.testapp.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


/**
 * This is the ViewModel class for the application, contains all business logic
 * @author Shreyash Singh
 * @version 1.0
 */
class MainViewModel(private val mainRepository: MainRepository, private val context: Context) :
        ViewModel() {


    /**
     * Live data that holds the data for TabOne and TabTwo Fragment
     */
    private val _data = MutableLiveData<Resource<List<Data>>>()
    val data: LiveData<Resource<List<Data>>>
        get() = _data

    /**
     * LiveData to store numbers in TabThree Fragment
     */
    private val _tabThreeData = MutableLiveData<Resource<ArrayList<Double>>>()
    val tabThreeData: LiveData<Resource<ArrayList<Double>>>
        get() = _tabThreeData


    /**
     * LiveData to store the Result of Finding the 2nd largest number
     */
    private val _tabThreeResult = MutableLiveData<Resource<TabThreeModel>>()
    val tabThreeResult: LiveData<Resource<TabThreeModel>>
        get() = _tabThreeResult


    //Holds temp viewHolders data for fragment to fragment Communication
    lateinit var clickedData: InnerData

    //Array of Number for TabThreeFragment
    private val tabThreeDataList = ArrayList<Double>()

    //Used to check for redundant numbers entered in TabThree Fragment
    private val tabThreeRepeatCheck = HashSet<Double>()

    init {
        getData()
    }

    /**
     *  Used to Get the data to be displayed in TabOne and TabTwo Fragment
     */
    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val dataModel = mainRepository.getData(context, R.raw.data)
                _data.postValue(Resource.Success(data = dataModel.data))
            } catch (e: Exception) {
                _data.postValue(Resource.Error(message = "" + e.localizedMessage))
            }
        }
    }

    /**
     * Used to Add Number to the list which is used to find the 2nd Largest Number
     * Number is added only if it isn't previously present
     *
     * @param data Number to be added in String format
     */
    fun addNumber(data: String) {

        if (data.isEmpty())
            return

        val number = data.trim().toDouble()

        if (tabThreeRepeatCheck.contains(number))
            return

        tabThreeRepeatCheck.add(number)
        tabThreeDataList.add(number)
        _tabThreeData.postValue(Resource.Success(data = ArrayList(tabThreeDataList)))
    }

    /**
     * To compute the 2nd Largest Number in an List of numbers and publish the Results
     */
    fun computeData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val number = find2largestNumber()
                val model = TabThreeModel(number.toString(), getAllNumbers())
                _tabThreeResult.postValue(Resource.Success(data = model))
            } catch (e: Exception) {

                _tabThreeResult.postValue(Resource.Error(message = "" + e.message))
            }

        }
    }


    /**
     * Find the 2nd Largest Number in the list
     * throws error if only single number is present
     *
     * @return 2nd largest Number
     */
    private fun find2largestNumber(): Double {
        val array = tabThreeDataList.toDoubleArray()

        var first = Double.MIN_VALUE
        var second = Double.MIN_VALUE

        if (array.size < 2)
            throw Exception("Not Enough Numbers")

        for (x in array) {
            if (x > first) {
                second = first
                first = x
            } else if (x > second && x != first)
                second = x
        }

        if (second == Double.MIN_VALUE)
            throw Exception("All Number Are the Same")

        return second
    }

    /**
     * Sorts(Descending) and returns all the entered numbers in String Format
     *
     * @return ALl numbers concatenated in String format
     */
    private fun getAllNumbers(): String {
        val tempList = ArrayList(tabThreeDataList)
        tempList.sortDescending()
        var data = ""

        for (number in tempList)
            data += "$number\n"

        return data
    }

    /**
     * Used to Re-compute 2nd largest number operation,
     * clears the existing data
     */
    fun clear() {
        tabThreeDataList.clear()
        tabThreeRepeatCheck.clear()
        _tabThreeData.postValue(Resource.Success(ArrayList(tabThreeDataList)))
    }
}