package com.example.rb_contact_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rb_contact_list.adapter.ClickListener
import com.example.rb_contact_list.adapter.UserAdapter
import com.example.rb_contact_list.databinding.FragmentHomeBinding
import com.example.rb_contact_list.modal.User
import com.example.rb_contact_list.viewmodel.ViewModel
import org.w3c.dom.Text
import java.util.*
import kotlin.math.log

class HomeFragment : Fragment(), ClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ///////////// Recycler View Adapter //////////////

        viewModel.allUsers.observe(viewLifecycleOwner, Observer { users ->
            val mAdapter = UserAdapter(users, this)
            binding.rvUserList.adapter = mAdapter

            binding.etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    mAdapter.filter.filter(s)
                }
            })
        })

        binding.rvUserList.layoutManager = LinearLayoutManager(context)

        /////////////////////////////////////////////////

        // click listener to add new user
        binding.addBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(null, false)
            findNavController().navigate(action)
        }
    }

    // click listener to detail screen
    override fun userClicked(user: User) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(user)
        findNavController().navigate(action)
    }
}