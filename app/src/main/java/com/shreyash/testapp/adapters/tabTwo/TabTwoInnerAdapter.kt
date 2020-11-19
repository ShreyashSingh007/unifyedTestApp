package com.shreyash.testapp.adapters.tabTwo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shreyash.testapp.R
import com.shreyash.testapp.interfaces.TabNavigator
import com.shreyash.testapp.models.InnerData
import kotlinx.android.synthetic.main.tab_one_inner_cell.view.*

/**
 * This is the Recycler View adapter class for inner RecyclerView inside TabTwoViewHolder fragment
 * @author Shreyash Singh
 * @version 1.0
 */

class TabTwoInnerAdapter(private val tabNavigator: TabNavigator) : RecyclerView.Adapter<TabTwoInnerAdapter.TabTwoInnerViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<InnerData>() {
        override fun areItemsTheSame(oldItem: InnerData, newItem: InnerData): Boolean {
            return oldItem.link == newItem.link
        }

        override fun areContentsTheSame(oldItem: InnerData, newItem: InnerData): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallBack)

    inner class TabTwoInnerViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        fun bind(data: InnerData) {
            itemView.iv_innerTab.load(data.link)
            itemView.tv_innerTabTitle.text = data.title
            itemView.tv_innerTabSubtitle.text = data.subtitle
            itemView.setOnClickListener {
                tabNavigator.onClicked(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabTwoInnerViewHolder {
        return TabTwoInnerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tab_two_inner_cell, parent, false))
    }

    override fun onBindViewHolder(holder: TabTwoInnerViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}