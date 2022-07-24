package com.shoxrux.passenger_route.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shoxrux.passenger_route.repository.UserRepository
import com.shoxrux.passenger_route.utils.NetworkHelper

class ViewModelFactory(private val userRepository: UserRepository,
                       val networkHelper: NetworkHelper
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(userRepository, networkHelper) as T
        }
        throw IllegalArgumentException("Error")
    }
}