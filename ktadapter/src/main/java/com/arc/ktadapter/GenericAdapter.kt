package com.arc.ktadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


class GenericAdapter<T : ListItemViewModel>(@LayoutRes val layoutId:Int,val BRViewModel: Int): RecyclerView.Adapter<GenericAdapter.GenericViewHolder<T>>() {


    private val items = mutableListOf<T>()
    private var inflater: LayoutInflater? = null
    private var onListItemViewClickListener: OnListItemViewClickListener? = null

    fun addItems(items:List<T>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnListItemViewClickListener(onListItemViewClickListener: OnListItemViewClickListener?){
        this.onListItemViewClickListener = onListItemViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val layoutInflater = inflater?: LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater,layoutId,parent,false)
        return GenericViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        val itemViewModel = items[position]
        itemViewModel.adapterPosition = position
        onListItemViewClickListener?.let{ itemViewModel.onListItemViewClickListener = it}
        holder.bind(itemViewModel,BRViewModel)
    }

    class GenericViewHolder<T : ListItemViewModel>(private val bindings: ViewDataBinding): RecyclerView.ViewHolder(bindings.root){
        fun bind(itemViewModel: T,BRViewModel: Int){
            bindings.setVariable(BRViewModel,itemViewModel)
            bindings.executePendingBindings()
        }
    }

    interface OnListItemViewClickListener{
        fun onClick(view: View, position:Int)
    }

}