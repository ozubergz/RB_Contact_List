package com.example.rb_contact_list.data

import androidx.lifecycle.LiveData
import com.example.rb_contact_list.modal.User


class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}