package com.example.uianimation.animation

import androidx.recyclerview.widget.DiffUtil
import com.example.uianimation.animation.MainListModel

class MainListDiffUtil(
    private val oldList: List<MainListModel>,
    private val newList: List<MainListModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

}