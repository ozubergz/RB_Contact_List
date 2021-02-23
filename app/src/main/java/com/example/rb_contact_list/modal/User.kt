package com.example.rb_contact_list.modal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User (
    val first_name : String,
    val last_name: String,
    val address: String,
    val email: ArrayList<String>,
    val phone: ArrayList<String>
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
