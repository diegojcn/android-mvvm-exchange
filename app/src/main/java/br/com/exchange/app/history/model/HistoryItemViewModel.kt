package br.com.exchange.app.history.model

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import br.com.exchange.app.base.utils.DateFormatUtils
import java.util.*

class HistoryItemViewModel(
    val id: Long?,
    val valueTo: Float?,
    val rateTo: Float?,
    val symbolTo: String,
    val symbolFrom: String,
    val valueFrom: Float?,
    val date: Date?
) {

    var to: ObservableField<String> = ObservableField<String>()

    var from: ObservableField<String> = ObservableField<String>()

    fun getRateToFormatted(): String {
        return rateTo.toString()
    }

    fun getValueToFormatted(): String {
        return "${symbolTo} - ${valueTo}"
    }

    fun getValueFromFormatted(): String {
        return  return "${symbolFrom} - ${valueFrom}"
    }

    fun getDateFormatted(): String {
        return DateFormatUtils.formatDate(date)
    }
}