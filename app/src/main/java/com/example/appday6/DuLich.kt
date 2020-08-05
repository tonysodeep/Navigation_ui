package com.example.appday6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_du_lich.*


class DuLich : Fragment() {
    private var linearLayoutManager: LinearLayoutManager? = null
    var locationArrayEating = ArrayList<DuLichData>()
    lateinit var tourAdapter: LocationAdapter
    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_du_lich, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.tour_rv)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        tourAdapter = LocationAdapter(createLocation(), activity!!)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.adapter = tourAdapter

        return rootView
    }
    fun createLocation(): ArrayList<DuLichData> {
        var location: DuLichData
        for (i in 1..10) {
            location = DuLichData(
                "05/03",
                "da lat" + i,
                "da lat tren vui" +
                        "\nciliekc me" +
                        "cuoc phieu luu ky thu",
                "https://i.ytimg.com/vi/vzIUb5F4TMo/maxresdefault.jpg",
                "05/03/2000",
                "toi" +
                        "\n rat la" +
                        "\n vui",
                R.drawable.editblue,
                R.drawable.shareblue,
                R.drawable.deletblue

            )
            locationArrayEating.add(location)
        }
        return locationArrayEating
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerForContextMenu(tour_rv)
    }

}