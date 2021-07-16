package com.example.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

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

    override fun onFinishInflate() {
        super.onFinishInflate()
        progressWithAnimation(180f)
    }

    fun progressWithAnimation(progress: Float) {
        val ofFloat = ObjectAnimator.ofFloat(this, "progress", 0f, progress)
        ofFloat.duration = 600
        ofFloat.interpolator = FastOutSlowInInterpolator()
        ofFloat.start()
    }

    private val margin = 20f

    private var progress = 90f

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f, height / 2f, width / 2f - margin, bgPaint)
        canvas.drawArc(
            margin,
            margin,
            width.toFloat() - margin,
            height.toFloat() - margin,
            -90f,
            progress,
            false,
            bgPaint2
        )
    }
}