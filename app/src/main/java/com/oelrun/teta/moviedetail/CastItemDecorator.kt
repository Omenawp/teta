package com.oelrun.teta.moviedetail

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.teta.R

class CastItemDecorator: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val pos = parent.getChildViewHolder(view).adapterPosition

        if (pos == 0) {
            outRect.left =
                parent.context.resources.getDimension(R.dimen.cast_item_margin_border).toInt()
        }

        if (pos == state.itemCount - 1) {
            outRect.right =
                parent.context.resources.getDimension(R.dimen.cast_item_margin_border).toInt()
        } else {
            outRect.right =
                parent.context.resources.getDimension(R.dimen.cast_item_margin).toInt()
        }
    }
}