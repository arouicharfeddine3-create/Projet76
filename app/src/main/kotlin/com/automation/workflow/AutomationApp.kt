package com.automation.workflow

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AutomationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Koin DI
        startKoin {
            androidContext(this@AutomationApp)
            // modules will be added here
        }
    }
}
