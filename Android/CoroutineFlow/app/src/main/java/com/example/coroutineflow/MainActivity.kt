package com.example.coroutineflow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coroutineflow.util.FlowUtil.inst
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        lifecycleScope.launch {
//            rendezcousChannel()
//            // どこからもchannelを受信しておらずリークする
//            edit.textChangeAsChannel().consumeEach {
//                text.text = Random.nextInt(1000).toString()
//            }
//        }

//        lifecycleScope.launch {
//            //複数の受信者に対応するためにBroadCastChannelに変換
//            val producer: BroadcastChannel<Int> = intStream().broadcast()
//            launch {
//                producer.consumeEach {
//                    Log.d("MYTAG", "f : $it")
//                    delay(10)
//                }
//            }
//            launch {
//                delay(60)
//                producer.consumeEach {
//                    Log.d("MYTAG", "s : $it")
//                    delay(10)
//                }
//            }
//        }
        val inst: Flow<Int> = inst()
        lifecycleScope.launch {
            inst.collect {
                Log.d("MYTAG", "recieve $it")
            }
        }

    }
}
