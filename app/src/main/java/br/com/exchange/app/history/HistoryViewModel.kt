package br.com.exchange.app.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.data.model.ConversionData
import br.com.exchange.app.history.HistoryItemViewModel
import br.com.mvvmexchange.data.dao.ConversionDao
import org.koin.core.KoinComponent
import org.koin.core.inject

class HistoryViewModel : ViewModel(), KoinComponent {

    private val dao: ConversionDao by inject()

    private val _mutableLiveData = MutableLiveData<List<HistoryItemViewModel>>()

    val data: LiveData<List<HistoryItemViewModel>> get() = _mutableLiveData

    fun getHistoryConversion() {

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

        _mutableLiveData.postValue(list)
    }
}