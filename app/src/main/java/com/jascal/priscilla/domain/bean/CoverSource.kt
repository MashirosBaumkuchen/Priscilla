package com.jascal.priscilla.domain.bean

import android.util.Log
import com.jascal.priscilla.domain.Cover
import com.jascal.priscilla.getHtml
import org.jsoup.Jsoup
import java.util.*

/**
 * @author jascal
 * @time 2018/8/22
 * describe
 */
class CoverSource : Source<ArrayList<Cover>> {
    override fun obtain(url: String): ArrayList<Cover> {
        val list = ArrayList<Cover>()

        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        Log.d("u17", doc.toString())

        val elements = doc.select("ul.comic_all").select("li")
        for (element in elements) {
            val coverUrl = element.select("img").attr("xsrc")
            val title = element.select("a").text()
            val link = element.select("a").attr("href")
            val cover = Cover(coverUrl, title, link)
            list.add(cover)
        }

        Log.d("u17", list.toString())

        return list
    }
}