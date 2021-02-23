package com.example.rb_contact_list.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rb_contact_list.modal.User

@TypeConverters(Converters::class)
@Database(entities = [User::class], version = 1)
abstract class UserDB : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDB? = null

        fun getUserDB(context: Context): UserDB {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java,
                    "user_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}