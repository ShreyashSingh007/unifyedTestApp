package com.shreyash.testapp.adapters.tabTwo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.shreyash.testapp.R
import com.shreyash.testapp.interfaces.TabNavigator
import com.shreyash.testapp.models.Data
import kotlinx.android.synthetic.main.tab_one_cell.view.*

/**
 * This is the Recycler View adapter class for TabTwo fragment
 * @author Shreyash Singh
 * @version 1.0
 */

class TabTwoAdapter(private val tabNavigator: TabNavigator) : RecyclerView.Adapter<TabTwoAdapter.TabTwoViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.heading == newItem.heading
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallBack)

    inner class TabTwoViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        fun bind(data: Data) {
            itemView.tv_headerTabOneFragCell.text = data.heading
            setUpRecyclerView(data)
        }

        /**
         * Sets up the inner Recycler View
         */
        private fun setUpRecyclerView(data: Data) {
            val lm = GridLayoutManager(itemView.context, 2)
            lm.orientation = GridLayoutManager.VERTICAL

            val innerAdapter = TabTwoInnerAdapter(tabNavigator)
            itemView.rv_innerTabOneCell.apply {
                layoutManager = lm
                adapter = innerAdapter
            }

            innerAdapter.differ.submitList(data.listData)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabTwoViewHolder {
        return TabTwoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tab_one_cell, parent, false))
    }

    override fun onBindViewHolder(holder: TabTwoViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}