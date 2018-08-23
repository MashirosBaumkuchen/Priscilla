package com.jascal.priscilla.ui.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jascal.priscilla.kits.recycler.AnotherBinder
import com.jascal.priscilla.kits.recycler.AnotherViewHolder
import com.jascal.priscilla.R
import com.jascal.priscilla.domain.Cover
import com.jascal.priscilla.loadUrl
import kotlinx.android.synthetic.main.item_cover.view.*

/**
 * @author jascal
 * @time 2018/8/22
 * describe
 */
class CoverBinder : AnotherBinder<Cover>() {
  override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): AnotherViewHolder {
    val view = inflater.inflate(R.layout.item_cover, parent, false)
    return AnotherViewHolder(view)
  }

  override fun renderView(holder: AnotherViewHolder, itemView: View, item: Cover) {
    itemView.tvCover.text = item.title
    itemView.ivCover.loadUrl(item.coverUrl)
  }
}