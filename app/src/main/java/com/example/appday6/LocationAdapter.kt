package com.example.appday6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.location.view.*

class LocationAdapter(private val locationList: ArrayList<DuLichData>, private val context: Context) :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(locationView: View) : RecyclerView.ViewHolder(locationView) {
        val locationDate: TextView = locationView.date
        val locationName: TextView = locationView.location_name
        val locationDescription: TextView = locationView.location_description
        val locationImage: ImageView = locationView.image_location
        val locationFullDate: TextView = locationView.full_date
        val locationFeedBack: TextView = locationView.location_feedback
        val buttonShare: ImageView = locationView.bt_share
        val buttonEdit: ImageView = locationView.bt_view
        val buttonDelet: ImageView = locationView.bt_delete
        val locationItem: LinearLayout = locationView.root_element
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        var locationView =
            LayoutInflater.from(parent.context).inflate(R.layout.location, parent, false)
        return LocationViewHolder(locationView)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val currentItem = locationList[position]
        holder.locationDate.text = currentItem.locaDate
        holder.locationName.text = currentItem.locaName
        holder.locationDescription.text = currentItem.locaDescription
        Glide.with(context)
            .load(currentItem.locaIamge)
            .into(holder.locationImage)
        holder.locationFullDate.text = currentItem.locaFullDate
        holder.locationFeedBack.text = currentItem.locaDescription
        holder.buttonShare.setImageResource(currentItem.btShare)
        holder.buttonEdit.setImageResource(currentItem.btEdit)
        holder.buttonDelet.setImageResource(currentItem.btDelet)
    }
}