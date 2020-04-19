package com.example.android.placebook.adapter

import android.app.Activity
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.android.placebook.R
import com.example.android.placebook.ui.MapsActivity
import com.example.android.placebook.viewmodel.MapsViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class BookmarkInfoWindowAdapter(val context: Activity) : GoogleMap.InfoWindowAdapter {

    private val contents: View

    init {
        contents = context.layoutInflater.inflate(R.layout.content_bookmark_info, null)
    }

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View? {
        val titleView = contents.findViewById<TextView>(R.id.title)
        titleView.text = marker.title ?: ""

        val phoneView = contents.findViewById<TextView>(R.id.phone)
        phoneView.text = marker.snippet ?: ""
        
        val imageView = contents.findViewById<ImageView>(R.id.photo)
        when (marker.tag) {

            is MapsActivity.PlaceInfo -> {
                imageView.setImageBitmap((marker.tag as MapsActivity.PlaceInfo).image)
            }

            is MapsViewModel.BookmarkMarkerView -> {
                val bookMarkView = marker.tag as MapsViewModel.BookmarkMarkerView
                imageView.setImageBitmap(bookMarkView.getImage(context))
            }
        }

        return contents
    }
}