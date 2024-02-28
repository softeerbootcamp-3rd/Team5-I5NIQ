package com.hyundai.myexperience.ui.main.my_page

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.hyundai.myexperience.R
import com.hyundai.myexperience.RECENT_COMMENT_IS_NULL
import com.hyundai.myexperience.UPCOMING_PROGRAM_IS_NULL
import com.hyundai.myexperience.utils.getProgramName

@BindingAdapter("setName")
fun setName(view: TextView, name: String?){
    view.text = name
}

@BindingAdapter("setLevel")
fun setLevel(view: ImageView, level: Int) {
    val drawableId = when (level) {
        1 -> R.drawable.mypage_iv_level_1
        2 -> R.drawable.mypage_iv_level_2
        3 -> R.drawable.mypage_iv_level_3
        else -> R.drawable.mypage_iv_level_0
    }
    view.setImageResource(drawableId)
}

@BindingAdapter("setParticipateFinCnt")
fun setParticipateFinCnt(view: TextView, cnt: Int){
    view.text = cnt.toString()
}

@BindingAdapter("setPayFinCnt")
fun setPayFinCnt(view: TextView, cnt: Int){
    view.text = cnt.toString()
}

@BindingAdapter("setCommentProgramName")
fun setCommentProgram(view: TextView, program: String){
    view.text = getProgramName(program)
}

@BindingAdapter("setCommentContent")
fun setCommentContent(view: TextView, content: String){
    if (content == "") {
        view.text = RECENT_COMMENT_IS_NULL
    }
    else {
        view.text = content
    }
}

@BindingAdapter("setUpcomingProgramCnt")
fun setUpcomingProgramCnt(view: TextView, cnt: Int){
    view.text = cnt.toString()
}

@BindingAdapter("setUpcomingProgramName")
fun setUpcomingProgramName(view: TextView, program: String){
    if (program == "") {
        view.text = UPCOMING_PROGRAM_IS_NULL
    }
    else {
        view.text = program
    }
}

@BindingAdapter("setUpcomingProgramDate")
fun setUpcomingProgramDate(view: TextView, date: String){
    view.text = date
}

@BindingAdapter("setIfUpcomingIsNull")
fun setIfUpcomingIsNull(view: TextView, isNull: Boolean){
    if (isNull) {
        view.visibility = View.INVISIBLE
    }
    else {
        view.visibility = View.VISIBLE
    }
}