package com.oelrun.teta.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoviesItemDecoration(
    private val spanCount: Int,
    private val itemOffset: Int,
    private val borderOffset: Int,
    private val bottomOffset: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val params = view.layoutParams as GridLayoutManager.LayoutParams

        if(params.spanSize == 1) {
            when(params.spanIndex) {
                0 -> {
                    outRect.left = borderOffset
                    outRect.right = itemOffset
                }
                spanCount - 1 -> {
                    outRect.left = itemOffset
                    outRect.right = borderOffset
                }
                else -> {
                    outRect.left = itemOffset
                    outRect.right = itemOffset
                }
            }
            outRect.bottom = bottomOffset
        } else {
            outRect.bottom = bottomOffset / 2
        }
    }

}
