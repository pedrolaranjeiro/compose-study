package com.ecommerce.demo

import android.app.Application
import com.ecommerce.demo.di.appModule
import com.ecommerce.demo.di.dataModule
import com.ecommerce.demo.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

private const val RAPID_API_KEY = "<insert-api-key>"

class ECommerceApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ECommerceApp)
            modules(dataModule(RAPID_API_KEY), domainModule, appModule)
        }
    }
}


