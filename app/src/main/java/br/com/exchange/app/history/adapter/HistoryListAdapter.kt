package br.com.exchange.app.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.exchange.R
import br.com.exchange.app.history.model.HistoryItemViewModel
import br.com.exchange.databinding.ItemHistoryBinding

class HistoryListAdapter(private val context: Context) :
    RecyclerView.Adapter<HistoryListAdapter.ViewHolder>() {

    private lateinit var items: List<HistoryItemViewModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListAdapter.ViewHolder {
        val binding: ItemHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_history, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryListAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun update(list: List<HistoryItemViewModel>) {
        this.items = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HistoryItemViewModel) {

            binding.viewModel = item
            binding.executePendingBindings()

        }
    }
}
