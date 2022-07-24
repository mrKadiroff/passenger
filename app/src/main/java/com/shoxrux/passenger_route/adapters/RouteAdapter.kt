package com.shoxrux.passenger_route.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shoxrux.passenger_route.database.entity.RouteEntity
import androidx.recyclerview.widget.ListAdapter
import com.shoxrux.passenger_route.databinding.RoutesLayoutBinding

class RouteAdapter  : ListAdapter<RouteEntity, RouteAdapter.Vh>(MyDiffUtil()){

    inner class Vh(var itemMainBinding: RoutesLayoutBinding) : RecyclerView.ViewHolder(itemMainBinding.root) {

        fun onBind(routeEntity: RouteEntity) {
            itemMainBinding.marshrut.text = routeEntity.marshrut
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RoutesLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    class MyDiffUtil: DiffUtil.ItemCallback<RouteEntity>(){
        override fun areItemsTheSame(oldItem: RouteEntity, newItem: RouteEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RouteEntity, newItem: RouteEntity): Boolean {
            return oldItem.equals(newItem)
        }


    }




}