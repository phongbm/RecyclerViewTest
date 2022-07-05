package com.phongbm.recyclerviewtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.phongbm.recyclerviewtest.databinding.ActivityMainBinding
import java.util.*

/**
 * Create by PhongBM on 07/05/2022
 */

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private lateinit var adapter: LineAdapter

    private val lines = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = TestLinearLayoutManager(this)
        layoutManager.setCallback(object : TestLinearLayoutManager.Callback {
            override fun getRecyclerViewMaxHeight(): Int {
                val maxHeight = (512 * resources.displayMetrics.density).toInt()
                Log.d(TAG, "getRecyclerViewMaxHeight()... $maxHeight")
                return maxHeight
            }

            override fun getRecyclerViewHeight(): Int {
                val height = binding.lstLine.height
                Log.d(TAG, "getRecyclerViewHeight()... $height")
                return height
            }

            override fun onRecyclerViewHeightChange(height: Int) {
                Log.d(TAG, "onRecyclerViewHeightChange()... $height")
                // TODO
            }
        })
        binding.lstLine.layoutManager = layoutManager

        binding.lstLine.setHasFixedSize(true)

        adapter = LineAdapter()
        binding.lstLine.adapter = adapter

        binding.btnAddLine.setOnClickListener {
            val line = "Data " + UUID.randomUUID().toString()
            lines.add(0, line)
            adapter.submitList(ArrayList(lines)) {
                Log.d(TAG, "submitList()...")
            }
        }

        binding.btnRemoveLine.setOnClickListener {
            lines.removeFirstOrNull()?.let {
                adapter.submitList(ArrayList(lines)) {
                    Log.d(TAG, "submitList()...")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}