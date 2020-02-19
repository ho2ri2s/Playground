package com.example.uianimation

import android.app.Application
import com.example.uianimation.ui.UiViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(module)
        }
    }

    private val module = module {
        viewModel { UiViewModel() }
    }
}