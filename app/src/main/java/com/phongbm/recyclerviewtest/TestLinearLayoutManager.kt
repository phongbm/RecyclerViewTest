package com.phongbm.recyclerviewtest

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by PhongBM on 07/05/2022
 */

class TestLinearLayoutManager : LinearLayoutManager {
    companion object {
        private const val TAG = "TestLinearLayoutManager"
    }

    private var callback: Callback = object : Callback {}

    constructor(context: Context) :
            super(context)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes)

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)

        Log.d(TAG, "onLayoutChildren()... $state")

        if (!state.isPreLayout) {
            val maxHeight = callback.getRecyclerViewMaxHeight()
            val currentHeight = callback.getRecyclerViewHeight()

            var totalHeight = 0

            val itemCount = state.itemCount
            for (i in 0 until itemCount) {
                val child = recycler.getViewForPosition(i)
                child.measure(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    View.MeasureSpec.makeMeasureSpec(currentHeight, View.MeasureSpec.UNSPECIFIED)
                )
                val childHeight = child.measuredHeight
                Log.d(TAG, "onLayoutChildren()... position = $i, height = $childHeight")

                totalHeight += childHeight

                if (totalHeight >= maxHeight) {
                    callback.onRecyclerViewHeightChange(maxHeight)
                    return
                }
            }

            if (totalHeight != currentHeight) {
                callback.onRecyclerViewHeightChange(totalHeight)
            }
        }
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        Log.d(TAG, "onLayoutCompleted()...")
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun getRecyclerViewMaxHeight(): Int = 0
        fun getRecyclerViewHeight(): Int = 0
        fun onRecyclerViewHeightChange(height: Int) = Unit
    }

}