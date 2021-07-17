package com.vigneshtheagarajan.mytest.di

import android.app.Application
import com.vigneshtheagarajan.mystore.di.networkModule
import com.vigneshtheagarajan.mystore.di.repositoryModule
import com.vigneshtheagarajan.mystore.di.viewModelModule
import com.vigneshtheagarajan.utils.one.app.UtilsLib
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        UtilsLib.initialize(this)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(repositoryModule, networkModule, viewModelModule))
        }



    }
}