package com.jascal.priscilla.domain

/**
 * @author jascal
 * @time 2018/8/22
 * describe
 */
data class Cover(val coverUrl: String, val title: String, val link: String)

data class Comic(val comicUrl: String)

data class Page(val title: String, val link: String)

data class Info(val updateTime: String, val description: String)

data class Chapter(val pages: List<Page>, val info: Info) {
    operator fun get(position: Int) = pages[position]
    val size
        get() = pages.size
}
