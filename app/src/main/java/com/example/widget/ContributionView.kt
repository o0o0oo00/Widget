package com.example.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.*

/**
 * @author:         zhaochunyu
 * @description:
 * @date:           2021/7/9
 */
class ContributionView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val monthDays by lazy {
        val instance = Calendar.getInstance()
        instance.time = Date(System.currentTimeMillis())
        val actualMaximum = instance.getActualMaximum(Calendar.DAY_OF_MONTH)
        (1..actualMaximum).toList()
    }

    private val paintBackground = Paint().apply {
        color = Color.GREEN
    }
    private val paintText = Paint().apply {
        color = Color.BLACK
        textAlign = Paint.Align.CENTER
        textSize = 45F
    }
    private val paintBorder = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.RED
        strokeWidth = 4F
    }

    private val rect = RectF()
    private var itemWidth = 0F
    private val itemMargin = 20
    private val round = 10F

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        itemWidth = measuredWidth / 10F - itemMargin
    }

    override fun onDraw(canvas: Canvas) {
        monthDays.forEachIndexed { index, i ->
            rect.left = (index % 10) * (itemWidth + itemMargin) + itemMargin / 2
            rect.top = (index / 10) * (itemWidth + itemMargin) + itemMargin / 2
            rect.right = rect.left + itemWidth
            rect.bottom = rect.top + itemWidth
            canvas.drawRoundRect(rect, round, round, paintBackground)
            canvas.drawRoundRect(rect, round, round, paintBorder)
            canvas.drawText(
                index.toString(),
                (rect.left + rect.right) / 2,
                (rect.top + rect.bottom) / 2 + (paintText.textSize - paintText.descent()) / 2,
                paintText
            )
        }
    }
}
