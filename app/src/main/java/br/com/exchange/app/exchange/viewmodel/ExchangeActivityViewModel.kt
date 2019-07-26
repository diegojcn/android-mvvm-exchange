package br.com.exchange.app.exchange.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

class ExchangeActivityViewModel: ViewModel(), KoinComponent {



    var value: ObservableField<String> = ObservableField<String>()

    var valueNew: ObservableField<String> = ObservableField<String>()

}