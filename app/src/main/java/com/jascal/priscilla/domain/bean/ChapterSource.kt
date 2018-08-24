package com.jascal.priscilla.domain.bean

import com.jascal.priscilla.domain.Chapter
import com.jascal.priscilla.domain.Info
import com.jascal.priscilla.domain.Page
import com.jascal.priscilla.getHtml
import org.jsoup.Jsoup
import java.util.*

/**
 * @author jascal
 * @time 2018/8/23
 * describe
 */
class ChapterSource : Source<Chapter> {
    override fun obtain(url: String): Chapter {
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        val pages = ArrayList<Page>()
        val elements = doc.select("ul.cf").select("li")

        for (element in elements) {
            val title = element.select("a").text()
            val link = element.select("a").attr("href")
            val page = Page(title, link)
            pages.add(page)
        }

        val updateTime = doc.select("div.fl").select("span").text()
        val detail = doc.select("p.words").text()
        val info = Info(updateTime, detail)

        return Chapter(pages, info)
    }

}