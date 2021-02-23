package com.example.rb_contact_list.data

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {

    @TypeConverter
    fun emailToString(arrayListProps: ArrayList<String>) : String {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = Moshi.Builder().build().adapter<ArrayList<String>>(type)
        return adapter.toJson(arrayListProps)
    }

    @TypeConverter
    fun stringToEmail(json: String) : ArrayList<String>? {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = Moshi.Builder().build().adapter<ArrayList<String>>(type)
        return adapter.fromJson(json)
    }
}