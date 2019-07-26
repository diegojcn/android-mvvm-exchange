package br.com.exchange.app.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.exchange.R
import br.com.exchange.databinding.ItemHistoryBinding

class HistoryListAdapter(private val context: Context) :
    RecyclerView.Adapter<HistoryListAdapter.ViewHolder>() {

    private var items: List<HistoryItemViewModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_history, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun update(list: List<HistoryItemViewModel>) {
        items = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HistoryItemViewModel) {

            binding.historyItem = item
            binding.executePendingBindings()

        }
    }
}
