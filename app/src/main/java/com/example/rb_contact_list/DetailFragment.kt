package com.example.rb_contact_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rb_contact_list.adapter.EmailAdapter
import com.example.rb_contact_list.adapter.PhoneAdapter
import com.example.rb_contact_list.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.user
        val emails = user.email
        val phoneNumbers = user.phone

        binding.tvFullName.text = "${user.first_name} ${user.last_name}"
        binding.tvAddress.text = "${user.address}"

//        Log.d("DetailFragment", "onViewCreated: $user")

        binding.rvEmailList.adapter = EmailAdapter(emails)
        binding.rvEmailList.layoutManager = LinearLayoutManager(context)

        binding.rvPhoneList.adapter = PhoneAdapter(phoneNumbers)
        binding.rvPhoneList.layoutManager = LinearLayoutManager(context)

        binding.btnEdit.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToEditFragment(user, true)
            findNavController().navigate(action)
        }
    }
}