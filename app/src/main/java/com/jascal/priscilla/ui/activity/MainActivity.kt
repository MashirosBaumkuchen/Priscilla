package com.jascal.priscilla.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.flying.xiaopo.poishuhui_kotlin.ui.adapter.ContentPagerAdapter
import com.jascal.priscilla.R
import com.jascal.priscilla.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * @author jascal
 * @time 2018/8/22
 * describe
 */
class MainActivity : AppCompatActivity() {
    companion object {
        val GITHUB_URL = "https://github.com/MashirosBaumkuchen/Priscilla"
    }

    val nameResList: ArrayList<Int> = arrayListOf(R.string.tab_one)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { visitGithub() }

        val fragments = ArrayList<Fragment>()

        fragments.add(HomeFragment())

        val nameList = nameResList.map(this::getString)

        viewPager.adapter = ContentPagerAdapter(fragments, nameList, supportFragmentManager)
        viewPager.offscreenPageLimit = 2

        tabLayout.setupWithViewPager(viewPager)
    }

    private fun visitGithub() {
        val uri = Uri.parse(GITHUB_URL);
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_about) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}