package com.jascal.priscilla.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.jascal.priscilla.R
import com.jascal.priscilla.snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import java.lang.Exception

/**
 * @author jascal
 * @time 2018/8/23
 * describe
 */
class ComicFragment : Fragment() {
  lateinit var progressBar: ProgressBar
  lateinit var iv_comic: ImageView
  lateinit var url: String;

  companion object {
    fun instance(url: String): ComicFragment {
      val fragment = ComicFragment()
      val bundle = Bundle()
      bundle.putSerializable("url", url)
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    url = arguments?.getString("url", "") ?: ""
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(R.layout.fragment_comic, container, false)
    progressBar = rootView.find(R.id.progressBar)
    iv_comic = rootView.find(R.id.iv_comic)
    return rootView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    progressBar.visibility = View.VISIBLE
  }


  override fun onResume() {
    super.onResume()
    Picasso.get().load(url).placeholder(R.color.material_deep_purple_50).into(iv_comic, object : Callback {
      override fun onSuccess() {
        progressBar.visibility = View.GONE
      }

      override fun onError(e: Exception?) {
        iv_comic.snackbar(context!!.resources.getString(R.string.network_error))
      }
    })
  }

}

