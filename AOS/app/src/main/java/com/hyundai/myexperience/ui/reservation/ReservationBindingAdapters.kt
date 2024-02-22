package com.hyundai.myexperience.ui.reservation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.hyundai.myexperience.utils.formatScheduleDate
import com.hyundai.myexperience.utils.formatScheduleTime

@BindingAdapter(value = ["step", "date", "session"], requireAll = true)
fun setSubTitle(view: TextView, step: Int, date: String?, session: String?) {
    if (step == 1) {
        view.text = date?.formatScheduleDate()
    } else if (step == 2) {
        view.text = session?.formatScheduleTime()
    }
}