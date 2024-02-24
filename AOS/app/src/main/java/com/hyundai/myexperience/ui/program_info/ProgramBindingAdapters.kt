package com.hyundai.myexperience.ui.program_info

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.hyundai.myexperience.R

@BindingAdapter("cars")
fun setCars(view: TextView, cars: List<String>?) {
    if (cars != null) {
        view.text = cars.joinToString()
    }
}

@BindingAdapter(value = ["duration", "maxNum"], requireAll = true)
fun setDurationAndMaxNum(view: TextView, duration: String?, maxNum: Int?) {
    view.text = view.context.getString(R.string.program_duration_and_max_num, duration, maxNum)
}
@BindingAdapter("qualificationTop")

fun setQualificationTop(view: TextView, qualification: String?) {
    if (qualification != null) {
        view.text = qualification.substring(0, qualification.indexOf('('))
    }
}

@BindingAdapter("qualificationBottom")
fun setQualificationBottom(view: TextView, qualification: String?) {
    if (qualification != null) {
        view.text = qualification.substring(qualification.indexOf('('))
    }
}