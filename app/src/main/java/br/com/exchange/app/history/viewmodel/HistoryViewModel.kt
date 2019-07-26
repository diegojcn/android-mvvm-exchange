package br.com.exchange.app.history.viewmodel

import androidx.lifecycle.ViewModel
import br.com.data.model.ConversionData
import br.com.exchange.app.history.model.HistoryItemViewModel
import br.com.mvvmexchange.data.dao.ConversionDao
import org.koin.core.KoinComponent
import org.koin.core.inject

class HistoryViewModel : ViewModel(), KoinComponent {

    private val dao: ConversionDao by inject()

    fun getHistoryConversion(): List<HistoryItemViewModel> {

        val list = mutableListOf<HistoryItemViewModel>()

        val allConversion: List<ConversionData> = dao.allConversion()


        for (item in allConversion) {


            list.add(
                HistoryItemViewModel(
                    item.id,
                    item.valueTo,
                    item.rateTo,
                    item.symbolTo,
                    item.symbolFrom,
                    item.valueFrom,
                    item.date
                )
            )

        }

        return list
    }
}