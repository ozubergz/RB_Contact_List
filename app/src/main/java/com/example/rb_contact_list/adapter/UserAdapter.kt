package com.example.rb_contact_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.rb_contact_list.databinding.UserLayoutBinding
import com.example.rb_contact_list.modal.User
import java.util.*

class UserAdapter(private var data: List<User>, private val listener: ClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>(), Filterable {

    var userFilteredList : List<User> = emptyList()

    init {
        userFilteredList = data
    }


    class ViewHolder(private val binding: UserLayoutBinding, private val listener: ClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: List<User>, position: Int) {
            val user = data[position]

            val firstChar = user.first_name.substring(0, 1).toUpperCase(Locale.ROOT)
            val lastChar = user.last_name.substring(0, 1).toUpperCase(Locale.ROOT)
            val abbreviation = "$firstChar$lastChar"

            //set header
            binding.tvHeader.text = firstChar
            if(position > 0 && data[position - 1].first_name.substring(0, 1) == user.first_name.substring(0, 1)) {
                binding.tvHeader.visibility = View.GONE
            } else {
                binding.tvHeader.visibility = View.VISIBLE
            }

            binding.circleLogo.text = abbreviation
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
        holder.bind(userFilteredList, position)
    }

    override fun getItemCount(): Int = userFilteredList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                userFilteredList = if(charSearch.isEmpty()) {
                    data
                } else {
                    val resultList : MutableList<User> = mutableListOf()
                    for(user in data) {
                        val firstName = user.first_name.toLowerCase(Locale.ROOT)
                        val lastName = user.last_name.toLowerCase(Locale.ROOT)
                        val fullName = "$firstName $lastName"
                        val search = charSearch.toLowerCase(Locale.ROOT)

                        if(fullName.contains(search)) {
                            resultList.add(user)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = userFilteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                userFilteredList = results?.values as List<User>
                notifyDataSetChanged()
            }

        }
    }

}