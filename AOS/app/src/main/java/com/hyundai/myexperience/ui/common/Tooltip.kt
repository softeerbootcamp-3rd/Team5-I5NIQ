package com.hyundai.myexperience.ui.common

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.hyundai.myexperience.R
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon

fun createTooltip(context: Context, message: String): Balloon {
    val balloon = Balloon.Builder(context)
        .setText(message)
        .setTextColor(context.getColor(R.color.white))
        .setTextSize(12f)
        .setPadding(8)
        .setBackgroundColor(context.getColor(R.color.gray7))
        .setArrowSize(10)
        .setCornerRadius(4f)
        .setTextTypeface(ResourcesCompat.getFont(context, R.font.pretendard_medium)!!)
        .setAutoDismissDuration(3500L)
        .setDismissWhenTouchOutside(true)
        .setDismissWhenClicked(true)
        .build()

    return balloon
}

fun createTooltipOrientationTop(context: Context, message: String): Balloon {
    val balloon = Balloon.Builder(context)
        .setText(message)
        .setTextColor(context.getColor(R.color.white))
        .setTextSize(12f)
        .setPadding(8)
        .setBackgroundColor(context.getColor(R.color.gray7))
        .setArrowSize(10)
        .setArrowOrientation(ArrowOrientation.TOP)
        .setCornerRadius(4f)
        .setTextTypeface(ResourcesCompat.getFont(context, R.font.pretendard_medium)!!)
        .setDismissWhenTouchOutside(true)
        .setDismissWhenClicked(true)
        .build()

    return balloon
}