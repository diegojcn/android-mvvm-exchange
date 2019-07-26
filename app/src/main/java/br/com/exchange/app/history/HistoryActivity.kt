package br.com.exchange.app.history

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.exchange.R
import br.com.exchange.app.base.BaseActivity
import br.com.exchange.databinding.ActivityHistoryBinding


class HistoryActivity : BaseActivity() {

    private val adapter: HistoryListAdapter by lazy {
        HistoryListAdapter(baseContext)
    }

    private val viewModel: HistoryViewModel by lazy {
        ViewModelProviders.of(this).get(HistoryViewModel::class.java)
    }

    private val binding: ActivityHistoryBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_history) as ActivityHistoryBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        viewModel.data.observe(this, Observer { adapter.update(it) })

        binding.toolbar.title = "HISTÓRICO DE CONVERSÃO"
        binding.toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        binding.toolbar.setNavigationOnClickListener { finish() }


        binding.recyclerEvents.adapter = adapter
        binding.recyclerEvents.layoutManager =
            LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)

        viewModel.getHistoryConversion()
    }

}
