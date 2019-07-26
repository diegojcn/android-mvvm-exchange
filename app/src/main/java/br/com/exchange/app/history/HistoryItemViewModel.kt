package br.com.exchange.app.history

import androidx.databinding.ObservableField
import br.com.exchange.app.base.utils.DateFormatUtils
import java.util.*

class HistoryItemViewModel(
    private val id: Long?,
    private val valueTo: Float?,
    private val rateTo: Float?,
    private val symbolTo: String,
    private val symbolFrom: String,
    private val valueFrom: Float?,
    private val date: Date?
) {

    val dateField = ObservableField<Date>(date)
    val symbolToField = ObservableField<String>(symbolTo)
    val valueToField = ObservableField<Float>(valueTo)
    
    fun getRateToFormatted(): String {
        return rateTo.toString()
    }

    fun getValueFromFormatted(): String {
        return  return "$symbolFrom - $valueFrom"
    }
    
}