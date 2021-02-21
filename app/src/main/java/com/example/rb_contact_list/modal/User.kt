package com.example.rb_contact_list.modal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val first_name : String,
    val last_name: String,
)
