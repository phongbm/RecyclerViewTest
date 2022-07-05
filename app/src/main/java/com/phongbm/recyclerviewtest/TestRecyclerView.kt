package com.phongbm.recyclerviewtest

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by PhongBM on 07/05/2022
 */

class TestRecyclerView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {
    companion object {
        private const val TAG = "TestRecyclerView"
    }

}