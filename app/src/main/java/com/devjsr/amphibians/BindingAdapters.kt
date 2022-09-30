package com.devjsr.amphibians

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devjsr.amphibians.network.Amphibian
import com.devjsr.amphibians.ui.AmphibianApiStatus
import com.devjsr.amphibians.ui.AmphibianListAdapter

@BindingAdapter("listData")
fun bindRecyclerView( recyclerView: RecyclerView, data: List<Amphibian>?) {
    val adapter = recyclerView.adapter as AmphibianListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus( statusImageView: ImageView, status: AmphibianApiStatus?) {
    when (status) {
        AmphibianApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        AmphibianApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        AmphibianApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        else -> {
            Log.d("BindingAdapters", "Error to load an status")
        }
    }
}