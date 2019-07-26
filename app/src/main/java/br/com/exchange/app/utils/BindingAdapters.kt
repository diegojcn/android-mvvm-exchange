package br.com.exchange.app.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.exchange.app.base.utils.DateFormatUtils
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("android:text")
fun setText(view: TextView, date: Date) {
    view.text = DateFormatUtils.formatDate(date)
}

@BindingAdapter(value = ["android:symbolTo", "android:valueTo"])
fun convertValueSelected(view: TextView, symbolTo: String, valueTo: Float) {
    view.text = "$symbolTo - $valueTo"
}
