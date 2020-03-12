package com.example.mfootbalapp.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mfootbalapp.R
import com.example.mfootbalapp.dashboard.app.fragment.DashboardFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(DashboardFragment())
    }



    private fun addFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_container, fragment)
        fragmentTransaction.commit()
    }
}
