package com.example.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * @author:         zhaochunyu
 * @description:
 * @date:           2021/7/15
 */
class SoftKeyView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var text = "1"
    fun setText(string: String) {
        text = string
    }

    private var corner = 10f
    private val bgPaint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
            color = Color.GRAY
            isAntiAlias = true
        }
    }
    private val textPaint by lazy {
        Paint().apply {
            color = Color.BLACK
            isAntiAlias = true
            textSize = 20f
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            corner,
            corner,
            bgPaint
        )
        canvas.drawText(
            "1",
            width / 2f,
            height / 2f + (textPaint.textSize - textPaint.descent()) / 2,
            textPaint
        )

    }
}