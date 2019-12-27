package com.example.coroutineflow.util

import android.util.Log
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object ChannelUtil {
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
}

/**
 * Channelのこれはメモリリークする
 */
fun TextView.textChangeAsChannel(): ReceiveChannel<String?>{
    val channel = Channel<String?>(Channel.CONFLATED) //最後に送信した値を保持する
    val textWatcher = addTextChangedListener {
        channel.offer(it?.toString())
    }
    // 親Scopeが閉じられるとchannelも閉じられる
    channel.invokeOnClose {
        removeTextChangedListener(textWatcher)
    }
    return channel
}

fun CoroutineScope.intStream(): ReceiveChannel<Int> = produce {
    repeat(10){
        Log.d("MYTAG", "send : $it")
        send(it)
    }
}