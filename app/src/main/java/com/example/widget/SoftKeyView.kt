package com.example.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationSet
import com.google.android.material.animation.AnimatorSetCompat

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
            text,
            width / 2f,
            height / 2f + (textPaint.textSize - textPaint.descent()) / 2,
            textPaint
        )

    }

    private val animationX by lazy {
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(this, "scaleX", 1f, 0.96f, 1f),
            ObjectAnimator.ofFloat(this, "scaleY", 1f, 0.96f, 1f)
        )
        animatorSet.duration = 100
        animatorSet
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                animationX.start()
            }
        }
        return super.onTouchEvent(event)
    }
}