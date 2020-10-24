package com.github.togetherproject.button.module.help_screens.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.togetherproject.button.databinding.ItemServiceBinding
import com.github.togetherproject.button.model.Services

class HelpServicesAdapter(
    private var list: List<Services> = emptyList()
): RecyclerView.Adapter<HelpServicesViewHolder>() {
    var onServiceSelect: ((service: Services) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HelpServicesViewHolder {
        val binding = ItemServiceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return HelpServicesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HelpServicesViewHolder, position: Int) {
        val service = list[position]
        holder.bind(service)
        holder.itemView.setOnClickListener{
            onServiceSelect?.invoke(service)
        }
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<Services>) {
        this.list = list
        notifyDataSetChanged()
    }
}