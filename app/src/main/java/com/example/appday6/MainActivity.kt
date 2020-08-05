package com.example.appday6

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_signin.view.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    private lateinit var duLichfragment: DuLich

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "app day 6"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        checkUser()

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                setTitle(R.string.app_name)

            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                setTitle(R.string.option)
            }
        }
        duLichfragment = DuLich()
        supportFragmentManager.beginTransaction().replace(R.id.fragment, duLichfragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        nav_view.setNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.home -> {
                    loadDuLich(DuLich())
                    true
                }
                R.id.home2 -> {
                    loadAnUong(AnUong())
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

    }

    fun loadDuLich(frag1: DuLich) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, frag1)
        ft.commit()
    }

    fun loadAnUong(frag1: AnUong) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, frag1)
        ft.commit()
    }

    fun checkUser() {
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val header: View = navigationView.getHeaderView(0)
        val teext: TextView = header.findViewById(R.id.user_name)
        val buttonLogin: Button = header.findViewById(R.id.bt_login)
        val buttonLogout: Button = header.findViewById(R.id.bt_logout)
        val sharedPreference = getSharedPreferences("Filename",Context.MODE_PRIVATE)
        var savedString = sharedPreference.getString("STRING_KEY",null)
        val editor = sharedPreference.edit()
        if (teext.text.toString() == "" && savedString == null) {
            Log.d("AAA", "null")
            buttonLogin.visibility = View.VISIBLE
            buttonLogout.visibility = View.GONE

        } else {
            teext.text = savedString
            Log.d("AAA", "not null")
            buttonLogout.visibility = View.VISIBLE
            buttonLogin.visibility = View.GONE
        }

        buttonLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Alert")
            builder.setMessage("Do you want to log out?")
            builder.setPositiveButton("Yes"){dialogInterface, which ->
                editor.remove("STRING_KEY")
                editor.apply()
                teext.text = ""
                buttonLogout.visibility = View.GONE
                buttonLogin.visibility = View.VISIBLE
            }
            builder.setNeutralButton("Cancel"){dialogInterface , which ->
                Toast.makeText(applicationContext,"Cancel",Toast.LENGTH_LONG).show()
            }
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()


        }
        buttonLogin.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_signin, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Login")
            val mAlertDialog = mBuilder.show()
            mDialogView.bt_save.setOnClickListener {
                mAlertDialog.dismiss()
                teext.text = mDialogView.username.text.toString()
                val sharedPreference = getSharedPreferences("Filename",Context.MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.apply{
                    putString("STRING_KEY", teext.text as String)
                }.apply()
                Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show()
                buttonLogin.visibility = View.GONE
                buttonLogout.visibility = View.VISIBLE
            }
            mDialogView.bt_cancel.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }


    }

}