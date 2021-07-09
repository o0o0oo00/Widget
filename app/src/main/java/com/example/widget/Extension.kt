package com.example.widget

import android.util.Log

/**
 * @author:         zhaochunyu
 * @description:
 * @date:           2021/7/9
 */
fun Any.log(s: String) {
    Log.e(this::class.java.simpleName, s)
}