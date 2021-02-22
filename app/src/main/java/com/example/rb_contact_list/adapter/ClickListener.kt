package com.example.rb_contact_list.adapter

import com.example.rb_contact_list.modal.User

interface ClickListener {
    fun userClicked(user: User)
}