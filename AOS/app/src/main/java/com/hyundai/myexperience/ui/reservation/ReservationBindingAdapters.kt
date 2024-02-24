package com.hyundai.myexperience.ui.reservation

import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.hyundai.myexperience.R
import com.hyundai.myexperience.utils.formatScheduleDate
import com.hyundai.myexperience.utils.formatScheduleTime
import com.hyundai.myexperience.utils.toCostWithSeparator

@BindingAdapter(value = ["step", "date", "session"], requireAll = true)
fun setSubTitle(view: TextView, step: Int, date: String?, session: String?) {
    if (step == 1) {
        view.text = date?.formatScheduleDate()
    } else if (step == 2) {
        view.text = session?.formatScheduleTime()
    }
}

@BindingAdapter("type", "step", "selectedProgramId", "selectedDate", "selectedSession")
fun setBtnEnabled(
    view: AppCompatButton,
    type: Int,
    step: Int,
    selectedProgramId: Int,
    selectedDate: String,
    selectedSession: String
) {

    fun setBtn(enabled: Boolean) {
        view.isClickable = enabled
        if (enabled) {
            view.background = ContextCompat.getDrawable(view.context, R.drawable.btn_background)
        } else {
            view.background =
                ContextCompat.getDrawable(view.context, R.drawable.btn_disabled_background)
        }
    }

    when (type) {
        0 -> when (step) {
            0 -> if (selectedProgramId == -1) {
                setBtn(false)
            } else {
                setBtn(true)
            }

            1 -> if (selectedDate == "") {
                setBtn(false)
            } else {
                setBtn(true)
            }

            2 -> if (selectedSession == "") {
                setBtn(false)
            } else {
                setBtn(true)
            }

            3 -> {
                view.background =
                    ContextCompat.getDrawable(view.context, R.drawable.btn_reservation_background)
            }
        }
    }
}

@BindingAdapter("company", "level")
fun setProgram(view: TextView, company: String, level: String) {
    view.text = view.context.getString(R.string.reservation_bottom_program, company, level)
}

@BindingAdapter("resultDate")
fun setResultDate(view: TextView, date: String) {
    view.text = date.formatScheduleDate()
}

@BindingAdapter("resultTime")
fun setResultTime(view: TextView, time: String) {
    view.text = time.formatScheduleTime()
}

@BindingAdapter("headCount", "participation")
fun setHeadCountAndParticipation(view: TextView, headCount: Int, participation: Boolean) {
    if (participation) {
        view.text = view.context.getString(R.string.reservation_headcount_participation, headCount)
    } else {
        view.text =
            view.context.getString(R.string.reservation_headcount_non_participation, headCount)
    }
}

@BindingAdapter("cost", "costCnt")
fun setCostString(view: TextView, cost: Int, costCnt: Int) {
    view.text = (cost * costCnt).toCostWithSeparator()
}