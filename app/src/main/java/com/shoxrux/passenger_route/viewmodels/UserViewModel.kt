package com.shoxrux.passenger_route.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoxrux.passenger_route.database.entity.RouteEntity
import com.shoxrux.passenger_route.repository.UserRepository
import com.shoxrux.passenger_route.utils.NetworkHelper
import com.shoxrux.passenger_route.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository,
                    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val liveRouteData = MutableLiveData<Resource<List<RouteEntity>>>()

//    fun getRoute(): LiveData<Resource<List<RouteEntity>>> {
//
//        viewModelScope.launch {
//            liveRouteData.postValue(Resource.loading(null))
//
//
//                try {
//                    coroutineScope {
//
//                        val async1 = async { userRepository.getRoutes() }
//
//                        val await1 = async1.await()
//                        liveRouteData.postValue(Resource.success(await1))
//
//                    }
//                }catch (e:Exception){
//                    liveRouteData.postValue(Resource.error(e.message ?: "Error",null))
//                }
//
//
//
//
//
//
//        }
//        return liveRouteData
//    }



}