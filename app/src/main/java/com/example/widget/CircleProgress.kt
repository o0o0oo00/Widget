package com.example.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @author:         zhaochunyu
 * @description:
 * @date:           2021/7/16
 */
class CircleProgress : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val bgPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = 30f
            color = Color.YELLOW
        }
    }
    private val bgPaint2 by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = 30f
            color = Color.BLUE
            strokeCap = Paint.Cap.ROUND
        }
    }

    private val margin = 20f

    fun setProgress() {

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f, height / 2f, width / 2f - margin, bgPaint)
        canvas.drawArc(
            margin,
            margin,
            width.toFloat() - margin,
            height.toFloat() - margin,
            -90f,
            90f,
            false,
            bgPaint2
        )
    }
}