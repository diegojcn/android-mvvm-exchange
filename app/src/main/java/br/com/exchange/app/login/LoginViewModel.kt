package br.com.exchange.app.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

class LoginViewModel: ViewModel(), KoinComponent{

    val username = ObservableField<String>()
    val password = ObservableField<String>()


}