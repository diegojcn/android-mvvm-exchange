package br.com.exchange.app.history

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.exchange.R
import br.com.exchange.app.base.BaseActivity
import br.com.exchange.app.history.adapter.HistoryListAdapter
import br.com.exchange.app.history.model.HistoryItemViewModel
import br.com.exchange.app.history.viewmodel.HistoryViewModel
import br.com.exchange.databinding.ActivityHistoryBinding
import org.jetbrains.anko.support.v4.onRefresh


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

        binding.toolbar.setTitle("HISTÓRICO DE CONVERSÃO")
        binding.toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        binding.toolbar.setNavigationOnClickListener {

            finish()

        }

        binding.recyclerEvents.adapter = adapter
        binding.recyclerEvents.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)


        binding.swipeEvents.onRefresh {
            val list: List<HistoryItemViewModel> = viewModel.getHistoryConversion()
            adapter.update(list)
            binding.swipeEvents.isRefreshing = false
        }

        val list: List<HistoryItemViewModel> = viewModel.getHistoryConversion()
        adapter.update(list)


    }


}
