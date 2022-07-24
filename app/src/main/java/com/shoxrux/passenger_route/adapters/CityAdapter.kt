package com.shoxrux.passenger_route.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shoxrux.passenger_route.database.entity.RouteEntity
import androidx.recyclerview.widget.ListAdapter
import com.shoxrux.passenger_route.database.entity.CityEntity
import com.shoxrux.passenger_route.databinding.Routes2LayoutBinding
import com.shoxrux.passenger_route.databinding.RoutesLayoutBinding

class CityAdapter(var onItemClickListener: OnItemClickListener)  : ListAdapter<CityEntity, CityAdapter.Vh>(MyDiffUtil()){

    inner class Vh(var itemMainBinding: Routes2LayoutBinding) : RecyclerView.ViewHolder(itemMainBinding.root) {

        fun onBind(routeEntity: CityEntity) {
            itemMainBinding.marshrut.text = routeEntity.cityy
            itemMainBinding.admin.setOnClickListener {
                onItemClickListener.onItemClick(routeEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(Routes2LayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    class MyDiffUtil: DiffUtil.ItemCallback<CityEntity>(){
        override fun areItemsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean {
            return oldItem.equals(newItem)
        }


    }

    interface OnItemClickListener{
        fun onItemClick(cityEntity: CityEntity)
    }




}