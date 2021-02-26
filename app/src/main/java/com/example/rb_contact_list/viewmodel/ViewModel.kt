package com.example.rb_contact_list.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.rb_contact_list.data.UserDB
import com.example.rb_contact_list.data.UserRepository
import com.example.rb_contact_list.modal.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: UserRepository

    init {
        val userDao = UserDB.getUserDB(application).userDao()
        repository = UserRepository(userDao)
//        allUsers = repository.allUsers
    }

    val allUsers = repository.allUsers.asLiveData(viewModelScope.coroutineContext)

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun update(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(user)
        }
    }
}