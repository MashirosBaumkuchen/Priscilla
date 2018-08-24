package com.jascal.priscilla.ui.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jascal.priscilla.R
import com.jascal.priscilla.domain.Page
import com.jascal.priscilla.kits.recycler.AnotherBinder
import com.jascal.priscilla.kits.recycler.AnotherViewHolder
import kotlinx.android.synthetic.main.item_page.view.*

/**
 * @author wupanjie
 */
class PageBinder : AnotherBinder<Page>() {
  override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): AnotherViewHolder {
    val itemView = inflater.inflate(R.layout.item_page, parent, false)
    return AnotherViewHolder(itemView)
  }

  override fun renderView(holder: AnotherViewHolder, itemView: View, item: Page) {
    itemView.tvPage.text = item.title
  }
}