package com.example.uianimation.animation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uianimation.ListActivity
import com.example.uianimation.R
import com.example.uianimation.databinding.ActivityMainBinding
import com.example.uianimation.databinding.NavDrawerBinding
import com.example.uianimation.ui.UiActivity

class MainActivity : AppCompatActivity() {

    companion object {
        var animationPlaybackSpeed: Double = 0.8
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navBinding: NavDrawerBinding

    private val mainListAdapter: MainListAdapter by lazy {
        MainListAdapter(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        with(binding) {
            // Appbar behavior Init
            (appbar.layoutParams as CoordinatorLayout.LayoutParams).behavior =
                ToolbarBehavior()

            // RecyclerView Init
            recyclerView.run {
                adapter = mainListAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
            }
            //Nav Drawer Init
            setUpNavDrawer(navDrawer)
            drawerIcon.setOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }

        }
    }

    fun setUpNavDrawer(navDrawer: NavDrawerBinding) {
        with(navDrawer) {
            animationSpeedSeekbar.setOnSeekbarChangeListener { value ->
                animationPlaybackSpeed = value as Double
                navDrawer.animationSpeedText.text = "${"%.1f".format(animationPlaybackSpeed)}x"
            }
            navigateUiActivity.setOnClickListener {
                val intent = Intent(this@MainActivity, UiActivity::class.java)
                startActivity(intent)
            }
            navigateListActivity.setOnClickListener {
                val intent = Intent(this@MainActivity, ListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
