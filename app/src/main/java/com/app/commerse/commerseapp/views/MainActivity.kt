package com.app.commerse.commerseapp.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.commerse.commerseapp.adapter.ListAdapter
import com.app.commerse.commerseapp.databinding.ActivityMainBinding
import com.app.commerse.commerseapp.models.WatchModel
import com.app.commerse.commerseapp.viewModel.MainViewModel
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var viewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Initialize contacts

        // Initialize contacts
        // Create adapter passing in the sample user data
        // Create adapter passing in the sample user data
        val adapter = ListAdapter()
        // Attach the adapter to the recyclerview to populate items
        // Attach the adapter to the recyclerview to populate items
        binding?.listWatches?.adapter = adapter
        viewModel?.listWatches?.observe(this, Observer { it ->
            Log.d("TAG", "onCreate: observed " + it.size)
            adapter.setWatches(it)
        })
        viewModel?.getWatchList("Tisto")


        binding?.tabs?.addTab(binding?.tabs!!.newTab().setText("Smart Watches"))
        binding?.tabs?.newTab()?.setText("Casio")?.let { binding?.tabs?.addTab(it) }
        binding?.tabs?.newTab()?.setText("Tisto")?.let { binding?.tabs?.addTab(it) }
        binding?.tabs?.newTab()?.setText("Silko")?.let { binding?.tabs?.addTab(it) }
        binding?.tabs?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                var watches: ArrayList<WatchModel> = adapter.getWatches() as ArrayList<WatchModel>
               adapter.setWatches( sortWatches(watches, tab.text as String?))
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun sortWatches(watches: ArrayList<WatchModel>, tag: String?): ArrayList<WatchModel> {
        var sortedWatches: ArrayList<WatchModel> = ArrayList<WatchModel>()
        for (watch in watches) {
            if (watch.category.equals(tag)) {
        sortedWatches.add(watch)
            }
        }
        return sortedWatches
    }
}