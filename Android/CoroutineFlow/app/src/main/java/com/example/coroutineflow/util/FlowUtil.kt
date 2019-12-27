package com.example.coroutineflow.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FlowUtil {

    fun inst(): Flow<Int> = flow {
        /**
         * withContext(Dispatchers.IO)はコンパイル時にエラーになる。
         * 基本的にはflow作成時と同じスレッドで実行される。
         * スレッドを変更するにはflowOnを使用
         */
        repeat(10) {
            delay(10)
            emit(it)
        }
    }
}