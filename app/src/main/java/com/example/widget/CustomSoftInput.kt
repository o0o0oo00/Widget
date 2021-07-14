package com.example.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView

/**
 * @author:         zhaochunyu
 * @description:
 * @date:           2021/7/14
 */
class CustomSoftInput : ViewGroup {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val rectF = RectF()
    private val rectFCorner = 10F
    private val bgPaint by lazy {
        Paint().apply {
            color = Color.GREEN
        }
    }

    init {
        (1 .. 12).toList().forEach {
            addView(AppCompatTextView(context).apply {
                text = it.toString()
                setBackgroundColor(Color.BLUE)
                gravity = Gravity.CENTER
            })
        }
    }

    private var childWidth = 0
    private var childHeight = 0
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        childWidth = measuredWidth / 4
        childHeight = childWidth / 2
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var currentLeft = l
        var currentTop = t
        (0 until childCount).map { getChildAt(it) }.forEachIndexed { index, view ->
            view.layout(
                currentLeft,
                currentTop,
                currentLeft + childWidth,
                currentTop + childHeight
            )
            currentTop = t + (index + 1) / 4 * childHeight
            currentLeft += childWidth
            if (index == 3 || index == 7 || index == 11) currentLeft = l
        }
    }
}