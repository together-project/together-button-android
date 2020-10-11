package com.github.togetherproject.button.module.help_services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.togetherproject.button.databinding.ServiceItemBinding
import com.github.togetherproject.button.model.Services
import com.github.togetherproject.button.utils.bold
import com.github.togetherproject.button.utils.toColor
import com.github.togetherproject.button.utils.underlined
import kotlinx.android.synthetic.main.service_item.view.*

class HelpServicesAdapter(
    private var list: List<Services> = emptyList()
): RecyclerView.Adapter<HelpServicesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpServicesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ServiceItemBinding.inflate(inflater)
        return HelpServicesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HelpServicesViewHolder, position: Int) {
        val name = list[position].name?.bold()
        val address = list[position].address
        val phone = list[position].phone?.toColor()?.underlined()
        val place = "$name: $address. $phone"
        holder.itemView.txt_place_all_infos.text = place
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: ArrayList<Services>) {
        this.list = list.toList()
        notifyDataSetChanged()
    }

}