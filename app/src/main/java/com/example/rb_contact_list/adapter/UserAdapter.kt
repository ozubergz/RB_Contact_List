package com.example.rb_contact_list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rb_contact_list.databinding.UserLayoutBinding
import com.example.rb_contact_list.modal.User

class UserAdapter(private val data: List<User>, private val listener: ClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(private val binding: UserLayoutBinding, private val listener: ClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvFullName.text = "${user.first_name} ${user.last_name}"

            binding.root.setOnClickListener {
                listener.userClicked(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = UserLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

}