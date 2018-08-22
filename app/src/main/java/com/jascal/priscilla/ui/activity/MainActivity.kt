package com.jascal.priscilla.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.jascal.priscilla.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    companion object {
        val GITHUB_URL = "https://github.com/MashirosBaumkuchen/Priscilla"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment(){
        setSupportActionBar(toolbar)
        fab.setOnClickListener { visitGithub() }
    }

    private fun visitGithub(){
        val uri = Uri.parse(GITHUB_URL);
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}