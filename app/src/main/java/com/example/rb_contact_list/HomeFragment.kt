package com.example.rb_contact_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rb_contact_list.adapter.ClickListener
import com.example.rb_contact_list.adapter.UserAdapter
import com.example.rb_contact_list.databinding.FragmentHomeBinding
import com.example.rb_contact_list.modal.User
import com.example.rb_contact_list.viewmodel.ViewModel

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

        viewModel.allUsers.observe(viewLifecycleOwner, Observer {
            binding.rvUserList.adapter = UserAdapter(it, this)
        })

        binding.rvUserList.layoutManager = LinearLayoutManager(context)

        /////////////////////////////////////////////////


        // click listener to add new user
        binding.addBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditFragment()
            findNavController().navigate(action)
        }
    }

    // click listener to detail screen
    override fun userClicked(user: User) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(user)
        findNavController().navigate(action)
    }
}