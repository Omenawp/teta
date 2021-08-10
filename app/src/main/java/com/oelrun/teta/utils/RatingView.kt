package com.oelrun.teta.utils

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.oelrun.teta.R

class RatingView@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var filled = ContextCompat.getDrawable(context, R.drawable.ic_star_filled)
    private var empty = ContextCompat.getDrawable(context, R.drawable.ic_star_blank)
    var rating = 0

    init {
        context.withStyledAttributes(attrs, R.styleable.RatingView) {
            rating = getInt(R.styleable.RatingView_rating, 0)
        }
    }

    override fun onDraw(canvas: Canvas) {
        for(i in 1..5) {
            val w = height * (i - 1)
            val pic = if(rating >= i) filled else empty
            pic?.setBounds(w,0, w + height, height)
            pic?.draw(canvas)
        }
    }
}