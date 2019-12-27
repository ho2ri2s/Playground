package com.example.uianimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uianimation.databinding.ActivityMainBinding
import com.example.uianimation.databinding.NavDrawerBinding

class MainActivity : AppCompatActivity(){

    companion object{
        var animationPlaybackSpeed: Double = 0.8
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navBinding: NavDrawerBinding

    private val mainListAdapter: MainListAdapter by lazy { MainListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding) {
            // Appbar behavior Init
            (appbar.layoutParams as CoordinatorLayout.LayoutParams).behavior = ToolbarBehavior()

            // RecyclerView Init
            recyclerView.run {
                adapter = mainListAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
            }
            //Nav Drawer Init
            navDrawer.animationSpeedSeekbar.setOnSeekbarChangeListener {value ->
                animationPlaybackSpeed = value as Double
                navDrawer.animationSpeedText.text = "${"%.1f".format(animationPlaybackSpeed)}x"
            }
            drawerIcon.setOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }

        }

    }
}
