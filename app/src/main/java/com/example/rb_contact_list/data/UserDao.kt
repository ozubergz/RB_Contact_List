package com.example.rb_contact_list.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rb_contact_list.modal.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user ORDER BY first_name ASC")
    fun getAllUsers(): LiveData<List<User>>

//    @Query("SELECT * from user WHERE id= :id LIMIT 1")
//    fun findUserById(id: Int)

//    @Transaction
//    suspend fun insertOrUpdate(user: User) {
//        val id = addUser(user)
//        if(id == -1L) {
//            updateUser(user)
//        }
//    }

//    @Query("SELECT * FROM user WHERE id= :id LIMIT 1")
//    fun findUserById(id: Int)

//    fun addOrUpdate(user: User) {
//        val user : User = findUserById(user.id)
//        if(user.isEmpty()) {
//            addUser(user)
//        } else {
//
//        }
//    }
}