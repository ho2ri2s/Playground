package com.example.coroutineflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.coroutineflow.util.ChannelUtil
import com.example.coroutineflow.util.ChannelUtil.rendezcousChannel
import com.example.coroutineflow.util.textChangeAsChannel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            rendezcousChannel()
            edit.textChangeAsChannel().consumeEach {
                text.text = Random.nextInt(1000).toString()
            }
        }
    }
}
