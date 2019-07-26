package br.com.exchange.app.exchange.viewmodel

import android.app.Activity
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import br.com.network.api.IExchangeRatesApi
import br.com.network.model.RateDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class ExchangeActivityViewModel : ViewModel(), KoinComponent {

    private val api: IExchangeRatesApi by inject()

    private lateinit var subscription: Disposable

    var valueTo: ObservableField<String> = ObservableField<String>()

    var valueFrom: ObservableField<String> = ObservableField<String>()

    var selectedTo: String = ""

    var selectedFrom: String = ""


    fun convert() {

        getRates(selectedTo, onSuccess = {

            val rate: Float = it.rates?.get(selectedFrom.let { selectedFrom })!!

            var valueTo = valueTo.get()?.toFloat()
            var valueFrom = valueTo?.times(rate)

            this.valueFrom.set("${selectedFrom} ${valueFrom.toString()}")

        }, onError = {

        })

    }

    private fun getRates(
        base: String, onSuccess: (rate: RateDTO) -> Unit, onError: (e: Throwable) -> Unit
    ) {

        this.subscription = this.api.getLatestByBase(base)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess(it) },
                { onError(it) }
            )

    }


}