package com.jascal.priscilla.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.jascal.priscilla.R
import com.jascal.priscilla.domain.Chapter
import com.jascal.priscilla.domain.Page
import com.jascal.priscilla.domain.bean.ChapterSource
import com.jascal.priscilla.kits.recycler.AnotherAdapter
import com.jascal.priscilla.snackbar
import com.jascal.priscilla.ui.binder.PageBinder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chapter.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.util.*

/**
 * @author jascal
 * @time 2018/8/23
 * describe
 */
class ChapterActivity : AppCompatActivity() {
    lateinit var url: String
    lateinit var pageList: RecyclerView
    lateinit var adapter: AnotherAdapter
    lateinit var pageRefresh: SwipeRefreshLayout
    lateinit var chapters: Chapter

    companion object {
        val INTENT_COVER_URL = "cover"
        val INTENT_URL = "url"
        val INTENT_TITLE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        setSupportActionBar(toolbar)
        init()
    }

    private fun init() {
        val coverUrl = intent.getStringExtra("cover")
        val title = intent.getStringExtra("title")
        url = intent.getStringExtra("link")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing_toolbar.title = title
        Picasso.get().load(coverUrl).into(backgroundImage)

        pageRefresh = find(R.id.pageRefresh)
        pageRefresh.setOnRefreshListener { load() }

        pageList = find(R.id.pageList)
        pageList.layoutManager = GridLayoutManager(this, 4)

        //TODO need to do better
        adapter = AnotherAdapter().with(Page::class.java, PageBinder().clickWith { _, position ->
            jump2Read(position)
        })

        pageList.adapter = adapter

    }


    override fun onResume() {
        super.onResume()
        pageRefresh.post { pageRefresh.isRefreshing = true }
        load()
    }

    private fun load() = doAsync {
        chapters = ChapterSource().obtain(url)
        val data = chapters.pages as ArrayList<Page>

        uiThread {
            adapter.update(data)
            pageRefresh.isRefreshing = false
            if (chapters.size == 0) {
                showError()
            }
        }
    }

    /**
     * to show error
     */
    private fun showError() {
        pageList.snackbar(resources.getString(R.string.page_load_error))
    }


    /**
     * jump to comic page
     * and special handle SBS
     */
    private fun jump2Read(position: Int) {
        val intent = Intent(this, ComicActivity().javaClass)
        intent.putExtra("url", chapters[position].link)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_chapter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
            return true
        } else if (id == R.id.action_info) {
            showBookInfo()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * to show the info of book
     */
    private fun showBookInfo() {
        val bookInfo = chapters.info
        pageList.snackbar(bookInfo.description + "\n" + bookInfo.updateTime)
    }
}
