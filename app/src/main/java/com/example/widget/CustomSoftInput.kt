package com.example.widget

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.AttributeSet
import android.util.Log
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

    private val vb by lazy {
        context.getSystemService(VIBRATOR_SERVICE) as? Vibrator
    }

    private val onItemClickListener = View.OnClickListener {
        Log.e("onItemClickListener","${it.tag}")
        vb?.vibrate(VibrationEffect.createOneShot(30,100))
    }
    init {
        (1..12).toList().forEach {
            val apply = SoftKeyView(context).apply {
                layoutParams = MarginLayoutParams(-2, -2).apply {
                    setMargins(marginHorizontal, marginVertical, marginHorizontal, marginVertical)
                }
                tag = it.toString()
                setText(it.toString())
                setOnClickListener(onItemClickListener)
            }
            addView(apply)
        }
    }

    private var childWidth = 0
    private var childHeight = 0
    private var marginHorizontal = 10
    private var marginVertical = 20
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        childWidth = measuredWidth / 4
        childHeight = childWidth / 2
        val w = childHeight - marginHorizontal * 2
        val ws = MeasureSpec.makeMeasureSpec(w, MeasureSpec.EXACTLY)
        val hs = MeasureSpec.makeMeasureSpec(childHeight - marginVertical * 2, MeasureSpec.EXACTLY)
        (0 until childCount).map { getChildAt(it) }.forEachIndexed { index, view ->
            measureChild(view, ws, hs)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var currentLeft = l
        var currentTop = t
        (0 until childCount).map { getChildAt(it) }.forEachIndexed { index, view ->
            view.layout(
                currentLeft + marginHorizontal,
                currentTop + marginVertical,
                currentLeft + childWidth - marginHorizontal,
                currentTop + childHeight - marginVertical
            )
            currentTop = t + (index + 1) / 4 * childHeight
            currentLeft += childWidth
            if (index == 3 || index == 7 || index == 11) currentLeft = l
        }
    }
}