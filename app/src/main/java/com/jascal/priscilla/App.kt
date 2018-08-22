package com.jascal.priscilla

import android.app.Application
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient

/**
 * @author jascal
 * @time 2018/8/21
 * describe
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val client = OkHttpClient()
        val picasso = Picasso.Builder(this)
                .downloader(OkHttp3Downloader(client))
                .build()

        Picasso.setSingletonInstance(picasso)
    }
}
