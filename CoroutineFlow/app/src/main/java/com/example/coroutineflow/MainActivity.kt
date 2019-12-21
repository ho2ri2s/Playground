package com.example.coroutineflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            rendezcousChannel()
        }
    }

    /**
    送信と受診が1:1のChannel
     */
    suspend fun rendezcousChannel() {
        Log.d("MYTAG", "start")
        val channel = Channel<Int>(Channel.RENDEZVOUS)
        GlobalScope.launch {
            repeat(5) {
                Log.d("MYTAG", "Send $it")
                channel.send(it) //受診されるまで中断する
                delay(1)
            }
            channel.close()
        }
        channel.consumeEach {
            //値が届くまで中断する
            Log.d("MYTAG", "Receive $it")
            delay(1)
        }
        Log.d("MYTAG", "End")
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
