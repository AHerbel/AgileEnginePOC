package com.aherbel.agileenginetest.app

import android.app.Application
import com.aherbel.agileenginetest.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AgileEngineTestApp: Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@AgileEngineTestApp)
            
            modules(appModule)
        }
    }
    
}