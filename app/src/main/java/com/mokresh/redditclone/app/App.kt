package com.mokresh.redditclone.app

import android.app.Application
import com.mokresh.redditclone.di.AppKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


open class App : Application() {


    override fun onCreate() {
        super.onCreate()

        initializeInjector()
    }

    //  initialize koin
    open fun initializeInjector() {
        startKoin {
            // declare used Android context
            androidLogger()
            androidContext(this@App)
            modules(AppKoinModules.getModules())
        }

    }
}