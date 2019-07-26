package br.com.exchange.app.exchange

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.exchange.R
import br.com.exchange.databinding.ActivityExchangeBinding
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import br.com.exchange.app.base.BaseActivity
import br.com.exchange.app.history.HistoryActivity
import org.jetbrains.anko.intentFor

class ExchangeActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private val viewModel: ExchangeActivityViewModel by lazy {
        ViewModelProviders.of(this).get(ExchangeActivityViewModel::class.java)
    }

    private val binding: ActivityExchangeBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_exchange)
                as ActivityExchangeBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.toolbar.subtitle = "CONVERSÃO DE MOEDA"
        binding.btnHistory.setOnClickListener {
            startActivity(intentFor<HistoryActivity>())
        }

        viewModel.symbols.observe(this, Observer {
            val dataAdapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item, it
            )
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerTo.adapter = dataAdapter
            binding.spinnerFrom.adapter = dataAdapter
        })

        viewModel.initializeSymbol()

        binding.spinnerTo.onItemSelectedListener = this
        binding.spinnerFrom.onItemSelectedListener = this

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(adapterView: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (adapterView?.tag.toString()) {
            "to" -> viewModel.selectedToSymbol = adapterView?.selectedItem.toString()
            "from" -> viewModel.selectedFromSymbol = adapterView?.selectedItem.toString()
        }
    }

}
