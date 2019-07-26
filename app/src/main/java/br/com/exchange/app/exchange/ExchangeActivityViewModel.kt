package br.com.exchange.app.exchange

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.data.model.ConversionData
import br.com.mvvmexchange.data.dao.ConversionDao
import br.com.network.api.IExchangeRatesApi
import br.com.network.model.RateDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class ExchangeActivityViewModel : ViewModel(), KoinComponent {

    private val endPoint: IExchangeRatesApi by inject()

    private val dao: ConversionDao by inject()

    private lateinit var subscription: Disposable

    private val _symbols = MutableLiveData<List<String>>()

    val symbols: LiveData<List<String>> get() = _symbols

    val valueTo = ObservableField<String>()

    val valueFrom = ObservableField<String>()

    var selectedToSymbol: String = ""

    var selectedFromSymbol: String = ""

    var msnError = ObservableField<String>("")

    fun convert() {
        msnError.set("")
        getRates(selectedToSymbol, onSuccess = { rateDTO ->

            rateDTO.rates?.let { map ->

                val rate: Float? = map.get(selectedFromSymbol)

                var valueTo = valueTo.get()?.toFloat()

                if (valueTo != null) {

                    rate?.let {
                        var valueFromTimesRate = valueTo?.times(it)
                        valueFrom.set("$selectedFromSymbol $valueFromTimesRate")

                        saveConversion(
                            valueTo,
                            rate,
                            selectedToSymbol,
                            selectedFromSymbol,
                            valueFromTimesRate
                        )
                    }

                } else {
                    msnError.set("Por favor, informe um valor para conversão")
                }
            }

        }, onError = {
            it.printStackTrace()
            msnError.set("Problema ao fazer a conversão!")
        })

    }

    fun initializeSymbol() {
        this.subscription = endPoint.getLatestRateBaseEuro()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    populateSymbols(it)
                },
                {
                    msnError.set("Problema ao recuperar as moedas.")
                }
            )
    }

    private fun populateSymbols(result: RateDTO) {

        var list = mutableListOf<String>()

        result.rates?.let {
            it.toList()?.forEach { item ->
                list.add(item.first)
            }
        }
        _symbols.postValue(list)
    }

    private fun getRates(
        base: String,
        onSuccess: (rate: RateDTO) -> Unit,
        onError: (e: Throwable) -> Unit
    ) {

        this.subscription = this.endPoint.getLatestByBase(base)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess(it) },
                { onError(it) }
            )
    }


    private fun saveConversion(
        valueTo: Float?,
        rateTo: Float?,
        symbolTo: String,
        symbolFrom: String,
        valueFrom: Float?
    ) {

        dao.insertEntity(
            ConversionData(
                null,
                valueTo,
                rateTo,
                symbolTo,
                symbolFrom,
                valueFrom,
                Date()
            )
        )

    }

}