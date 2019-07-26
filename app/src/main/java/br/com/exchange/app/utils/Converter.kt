package br.com.exchange.app.utils

import android.widget.TextView
import androidx.databinding.InverseMethod
import br.com.exchange.app.base.utils.DateFormatUtils

object Converter {
    @InverseMethod("dateToString")
    fun dateToString(
        view: TextView, oldValue: String,
        value: String
    ): String {
        return DateFormatUtils.formatDate(view.text.toString())
    }
    
}