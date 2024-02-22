package com.hyundai.myexperience.ui.main.my_page

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setName")
fun setName(view: TextView, name: String?){
    view.text = name
}

@BindingAdapter("setLevel")
fun setLevel(view: ImageView, level: Int){

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
    view.text = program
}

@BindingAdapter("setCommentContent")
fun setCommentContent(view: TextView, content: String){
    if (content == "") {
        view.text = "최근 남긴 댓글이 없습니다."
    }
    else {
        view.text = content
    }
}
