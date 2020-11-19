package com.shreyash.testapp.adapters.tabThree

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shreyash.testapp.R
import kotlinx.android.synthetic.main.tab_three_cell.view.*

/**
 * This is the Recycler View adapter class for Displaying the Entered Numbers
 * @author Shreyash Singh
 * @version 1.0
 */

class TabThreeAdapter : RecyclerView.Adapter<TabThreeAdapter.TabThreeViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<Double>() {
        override fun areItemsTheSame(oldItem: Double, newItem: Double): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Double, newItem: Double): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    class TabThreeViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        fun bind(data: Double) {
            itemView.tv_number.text = data.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabThreeViewHolder {
        return TabThreeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tab_three_cell, parent, false))
    }

    override fun onBindViewHolder(holder: TabThreeViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}