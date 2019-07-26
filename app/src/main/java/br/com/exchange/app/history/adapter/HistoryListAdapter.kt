package br.com.exchange.app.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.data.model.ConversionData
import br.com.exchange.R
import br.com.exchange.app.history.model.HistoryItemViewModel
import br.com.exchange.databinding.ItemHistoryBinding

class PostListAdapter: RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private lateinit var postList:List<ConversionData>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListAdapter.ViewHolder {
        val binding: ItemHistoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_history, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostListAdapter.ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return if(::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList:List<ConversionData>){
        this.postList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemHistoryBinding):RecyclerView.ViewHolder(binding.root){
       private val viewModel = HistoryItemViewModel()
        fun bind(post:ConversionData){
//            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}
