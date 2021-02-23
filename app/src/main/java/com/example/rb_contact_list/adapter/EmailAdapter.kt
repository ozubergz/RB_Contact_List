package com.example.rb_contact_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rb_contact_list.databinding.EmailLayoutBinding

class EmailAdapter(private val emails: ArrayList<String>) : RecyclerView.Adapter<EmailAdapter.ViewHolder>() {

    class ViewHolder(private val binding: EmailLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(email: String) {
            binding.tvEmail.text = email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = EmailLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(emails[position])
    }

    override fun getItemCount(): Int = emails.size

}