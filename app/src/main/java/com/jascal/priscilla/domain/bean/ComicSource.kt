package com.jascal.priscilla.domain.bean

import android.util.Log
import com.jascal.priscilla.domain.Comic
import com.jascal.priscilla.getHtml
import org.jsoup.Jsoup
import java.util.*

/**
 * @author jascal
 * @time 2018/8/23
 * describe
 */
class ComicSource : Source<ArrayList<Comic>> {
    override fun obtain(url: String): ArrayList<Comic> {
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        Log.d("u17", "comic==============================")

        val elements = doc.select("div.comic_read_img")
        val list = ArrayList<Comic>()

        Log.d("u17", elements.toString())

        for (element in elements) {
            var comicUrl: String
            val temp = element.attr("src")
            if (temp.contains(".png") || temp.contains(".jpg") || temp.contains(".JPEG")) {
                comicUrl = temp
            } else {
                continue
            }

            val comic = Comic(comicUrl)
            list.add(comic)
        }

        Log.d("u17", "list=$list")
        return list
    }
}