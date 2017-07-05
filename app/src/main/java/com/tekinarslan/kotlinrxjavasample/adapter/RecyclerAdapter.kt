package com.tekinarslan.kotlinrxjavasample.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.tekinarslan.kotlinrxjavasample.R
import com.tekinarslan.kotlinrxjavasample.model.PhotosDataModel
import kotlinx.android.synthetic.main.item_recycler_view.view.*
import java.util.*


/**
 * Created by selimtekinarslan on 6/29/2017.
 */
class RecyclerAdapter(var items: ArrayList<PhotosDataModel>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<PhotosDataModel>) {
        this.items = items as ArrayList<PhotosDataModel>
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_recycler_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        items[position].let {
            holder?.title?.text = it.title
            holder?.subtitle?.text = it.subTitle
            Glide.with(holder?.image?.context)
                    .load(it.thumbnailUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder?.image)
        }
    }

    class ViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val card = rootView.card
        val title = rootView.title
        val subtitle = rootView.subtitle
        val image = rootView.image

    }
}