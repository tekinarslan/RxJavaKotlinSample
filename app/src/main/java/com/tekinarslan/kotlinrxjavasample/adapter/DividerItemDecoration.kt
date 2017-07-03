package com.tekinarslan.kotlinrxjavasample.adapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by selimtekinarslan on 6/30/2017.
 */
class DividerItemDecoration(var space: Int?) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.bottom = space
        outRect?.top = space
    }
}