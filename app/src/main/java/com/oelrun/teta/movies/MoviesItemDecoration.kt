package com.oelrun.teta.movies

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoviesItemDecoration(
    private val width: Int,
    private val spanCount: Int,
    private val borderOffset: Int,
    private val bottomOffset: Int): RecyclerView.ItemDecoration() {

    private var space = 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val params = view.layoutParams as GridLayoutManager.LayoutParams

        if(params.spanSize != spanCount) {
            if(space == 0){
                val all = width - params.width * spanCount - borderOffset * 2
                space = all / (spanCount - 1) / 2
            }

            when(params.spanIndex) {
                0 -> {
                    outRect.left = borderOffset
                    outRect.right = space
                }
                spanCount - 1 -> {
                    outRect.left = space
                    outRect.right = borderOffset
                }
                else -> {
                    outRect.left = space
                    outRect.right = space
                }
            }
            outRect.bottom = bottomOffset
        } else {
            outRect.bottom = bottomOffset / 2
        }
    }

}
