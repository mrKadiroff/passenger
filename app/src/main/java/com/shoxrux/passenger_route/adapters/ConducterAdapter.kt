package com.shoxrux.passenger_route.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoxrux.passenger_route.database.entity.ConducterEntity
import com.shoxrux.passenger_route.database.entity.RouteEntity
import com.shoxrux.passenger_route.databinding.ConducterLayoutBinding
import com.shoxrux.passenger_route.databinding.RoutesLayoutBinding

class ConducterAdapter : ListAdapter<ConducterEntity, ConducterAdapter.Vh>(MyDiffUtil()){

    inner class Vh(var itemMainBinding: ConducterLayoutBinding) : RecyclerView.ViewHolder(itemMainBinding.root) {

        fun onBind(conducterEntity: ConducterEntity) {
            itemMainBinding.name.text = conducterEntity.name
            itemMainBinding.surname.text = conducterEntity.surname
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ConducterLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    class MyDiffUtil: DiffUtil.ItemCallback<ConducterEntity>(){
        override fun areItemsTheSame(oldItem: ConducterEntity, newItem: ConducterEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ConducterEntity,
            newItem: ConducterEntity
        ): Boolean {
            return oldItem.equals(newItem)
        }


    }




}