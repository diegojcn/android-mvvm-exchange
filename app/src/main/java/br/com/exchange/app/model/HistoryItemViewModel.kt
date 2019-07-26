package br.com.exchange.app.model

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class HistoryItemViewModel: ViewModel() {


    var to: ObservableField<String> = ObservableField<String>()

    var from: ObservableField<String> = ObservableField<String>()
}