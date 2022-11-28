package com.aq.lovelocal.util

import android.content.Context
import android.graphics.*
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.core.content.ContextCompat
import com.aq.lovelocal.R
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(private val mDividerSize: Int, private val context: Context) : ItemDecoration() {
    private val mPaint: Paint = Paint()
    init {
        mPaint.color = ContextCompat.getColor(context, R.color.dark_gray)
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mDividerSize.toFloat()
        mPaint.pathEffect = DashPathEffect(floatArrayOf(15f, 10f), 0f)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = mDividerSize
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        val path = Path()
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin + mDividerSize / 2
            path.moveTo(left.toFloat(), top.toFloat())
            path.lineTo(right.toFloat(), top.toFloat())
        }
        c.drawPath(path, mPaint)
    }
}