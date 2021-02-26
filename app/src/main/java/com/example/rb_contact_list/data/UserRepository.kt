package com.example.rb_contact_list.data

import androidx.lifecycle.LiveData
import com.example.rb_contact_list.modal.User
import kotlinx.coroutines.flow.Flow


class UserRepository(private val userDao: UserDao) {

    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun update(user: User) {
        userDao.updateUser(user)
    }
}