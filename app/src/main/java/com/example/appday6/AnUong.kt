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
import kotlinx.android.synthetic.main.fragment_an_uong.*
import kotlinx.android.synthetic.main.fragment_du_lich.*


class AnUong : Fragment() {

    private var linearLayoutManager: LinearLayoutManager? = null
    var locationArrayEating = ArrayList<DuLichData>()
    lateinit var foodAdapter: FoodAdapter

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_an_uong, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.food_rv)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        foodAdapter = FoodAdapter(createLocation(), activity!!)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.adapter = foodAdapter
        return rootView
    }

    fun createLocation(): ArrayList<DuLichData> {
        var location: DuLichData
        for (i in 1..10) {
            location = DuLichData(
                "05/03",
                "vung tau" + i,
                "da lat tren vui" +
                        "\nciliekc me" +
                        "cuoc phieu luu ky thu",
                "https://www.dalattrip.com/media/2012/10/Dalat-Vietnam-Dalat-central-lake.jpg",
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
        registerForContextMenu(food_rv)
    }
}