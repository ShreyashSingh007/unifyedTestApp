package com.shreyash.testapp.adapters.tabOne

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shreyash.testapp.R
import com.shreyash.testapp.interfaces.TabNavigator
import com.shreyash.testapp.models.Data
import kotlinx.android.synthetic.main.tab_one_cell.view.*


/**
 * This is the Recycler View adapter class for TabOne fragment
 * @author Shreyash Singh
 * @version 1.0
 */

class TabOneAdapter(private val tabNavigator: TabNavigator) : RecyclerView.Adapter<TabOneAdapter.TabOneViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.heading == newItem.heading
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallBack)

    /**
     * Sets up the inner Recycler View
     */
    inner class TabOneViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        fun bind(data: Data) {
            itemView.tv_headerTabOneFragCell.text = data.heading
            setUpRecyclerView(data)
        }

        private fun setUpRecyclerView(data: Data) {
            val lm = LinearLayoutManager(itemView.context)
            lm.orientation = LinearLayoutManager.HORIZONTAL

            val innerAdapter = TabOneInnerAdapter(tabNavigator)
            itemView.rv_innerTabOneCell.apply {
                layoutManager = lm
                adapter = innerAdapter
            }

            innerAdapter.differ.submitList(data.listData)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabOneViewHolder {
        return TabOneViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tab_one_cell, parent, false))
    }

    override fun onBindViewHolder(holder: TabOneViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}