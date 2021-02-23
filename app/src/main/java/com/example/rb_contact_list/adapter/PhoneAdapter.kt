package com.example.rb_contact_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rb_contact_list.databinding.EmailLayoutBinding
import com.example.rb_contact_list.databinding.PhoneLayoutBinding

class PhoneAdapter(private val phoneNumbers: ArrayList<String>) : RecyclerView.Adapter<PhoneAdapter.ViewHolder>() {

    class ViewHolder(private val binding: PhoneLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(phone: String) {
            binding.tvPhoneNumber.text = phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PhoneLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(phoneNumbers[position])
    }

    override fun getItemCount(): Int = phoneNumbers.size

}