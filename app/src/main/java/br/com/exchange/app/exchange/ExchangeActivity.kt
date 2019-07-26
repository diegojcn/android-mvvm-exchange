package br.com.exchange.app.exchange

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.exchange.R
import br.com.exchange.app.base.BaseActivity
import br.com.exchange.app.exchange.viewmodel.ExchangeActivityViewModel
import br.com.exchange.databinding.ActivityExchangeBinding
import org.koin.core.KoinComponent
import android.widget.ArrayAdapter
import br.com.network.api.ISymbolApi
import br.com.network.model.SupportedSymbolsDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.inject


class ExchangeActivity : BaseActivity(), KoinComponent {


    private val symbolApi: ISymbolApi by inject()

    private lateinit var subscription: Disposable

    private val viewModel: ExchangeActivityViewModel by lazy {

        ViewModelProviders.of(this).get(ExchangeActivityViewModel::class.java)
    }

    private val binding: ActivityExchangeBinding by lazy {

        DataBindingUtil.setContentView(this, R.layout.activity_exchange) as ActivityExchangeBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        binding.viewModel = viewModel

        this.inicializeSymbol()

        binding.btnconverter.setOnClickListener {


        }
        super.onCreate(savedInstanceState)

    }

    private fun inicializeSymbol() {

        this.subscription = this.symbolApi.getSymbol("460eb12dfa488985d136a58377040a33")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onRetrieveAuthSuccess(it) },
                { onRetrieveAuthError(it) }
            )

    }

    private fun onRetrieveAuthError(it: Throwable) {
    it.printStackTrace()
    }

    private fun onRetrieveAuthSuccess(result: SupportedSymbolsDTO) {

        val symbols = populateSymbols(result)

        val dataAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, symbols
        )
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(dataAdapter)
        binding.spinnerDesiredCoin.setAdapter(dataAdapter)

    }

    private fun populateSymbols(result: SupportedSymbolsDTO): List<String> {

        var list = mutableListOf<String>()


        if(!result.symbols.isNullOrEmpty()){

            for (item in result.symbols!!.toList()){
                list.add(item.first)
            }
        }

        return list
    }
}
