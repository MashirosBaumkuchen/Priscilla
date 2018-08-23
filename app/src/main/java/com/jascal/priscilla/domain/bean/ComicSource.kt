package com.jascal.priscilla.domain.bean

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

    val elements = doc.select("div.comic_read_img").select("div")
    val list = ArrayList<Comic>()

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
    return list
  }
}