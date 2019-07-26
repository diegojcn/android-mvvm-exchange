package br.com.exchange.app.exchange

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.exchange.R
import br.com.exchange.app.base.BaseActivity
import br.com.exchange.app.exchange.viewmodel.ExchangeActivityViewModel
import br.com.exchange.databinding.ActivityExchangeBinding
import org.koin.core.KoinComponent
import android.widget.ArrayAdapter
import br.com.exchange.app.hideKeyboard
import br.com.exchange.app.history.HistoryActivity
import br.com.network.api.IExchangeRatesApi
import br.com.network.model.RateDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.intentFor
import org.koin.core.inject


class ExchangeActivity : BaseActivity(), KoinComponent {


    private val api: IExchangeRatesApi by inject()

    private lateinit var subscription: Disposable

    private val viewModel: ExchangeActivityViewModel by lazy {

        ViewModelProviders.of(this).get(ExchangeActivityViewModel::class.java)
    }

    private val binding: ActivityExchangeBinding by lazy {

        DataBindingUtil.setContentView(this, R.layout.activity_exchange) as ActivityExchangeBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        binding.viewModel = viewModel
        binding.toolbar.setSubtitle("CONVERSÃO DE MOEDA")
        binding.historyBtn.setOnClickListener{
            startActivity(intentFor<HistoryActivity>())
        }

        this.initializeSymbol()

        val onClickListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                hideKeyboard()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                when (p0?.tag.toString()) {

                    "to" -> viewModel.selectedTo = p0?.selectedItem.toString()
                    "from" -> viewModel.selectedFrom = p0?.selectedItem.toString()

                }
            }

        }


        binding.spinnerTo.onItemSelectedListener = onClickListener
        binding.spinnerFrom.onItemSelectedListener = onClickListener

        binding.btnconverter.setOnClickListener {
            viewModel.convert()
            hideKeyboard()
        }
        super.onCreate(savedInstanceState)

    }

    private fun initializeSymbol() {

        this.subscription = this.api.getLatestRateBaseEuro()
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

    private fun onRetrieveAuthSuccess(result: RateDTO) {

        val symbols = populateSymbols(result)

        val dataAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, symbols
        )
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerTo.setAdapter(dataAdapter)
        binding.spinnerFrom.setAdapter(dataAdapter)

    }

    private fun populateSymbols(result: RateDTO): List<String> {

        var list = mutableListOf<String>()


        if (!result.rates.isNullOrEmpty()) {

            for (item in result.rates!!.toList()) {
                list.add(item.first)
            }
        }

        return list
    }
}
