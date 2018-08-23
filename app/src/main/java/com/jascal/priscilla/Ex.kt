package com.jascal.priscilla

import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import okhttp3.Request

/**
 * @author jascal
 * @time 2018/8/22
 * describe
 */
fun getHtml(url: String): String {
    val client = OkClient.instance
    val request = Request.Builder()
            .url(url)
            .build()

    val response = client.newCall(request).execute()
    return response.body()?.string() ?: ""
}

fun ImageView.loadUrl(url: String) {
    Picasso.get().load(url).into(this)
}

fun View.snackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}