package com.example.rb_contact_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rb_contact_list.adapter.EmailAdapter
import com.example.rb_contact_list.adapter.PhoneAdapter
import com.example.rb_contact_list.databinding.FragmentEditBinding
import com.example.rb_contact_list.modal.User
import com.example.rb_contact_list.viewmodel.ViewModel

class EditFragment : Fragment() {

    val TAG = "EditFragment"

    private lateinit var binding: FragmentEditBinding
    private val args by navArgs<EditFragmentArgs>()
    private val viewModel by activityViewModels<ViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = FragmentEditBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.user
        val edit = args.edit

        var emails : ArrayList<String> = arrayListOf()
        var phoneNumbers : ArrayList<String> = arrayListOf()

        if (user != null) {
            emails = user.email
            binding.rvEmailList.adapter = EmailAdapter(emails)
            binding.rvEmailList.layoutManager = LinearLayoutManager(context)

            phoneNumbers = user.phone
            binding.rvPhoneList.adapter = PhoneAdapter(phoneNumbers)
            binding.rvPhoneList.layoutManager = LinearLayoutManager(context)
        }

        binding.btnAddEmail.setOnClickListener {
            val newEmail = binding.etEmail.text.toString()
            if (newEmail != "") {
                emails.add(newEmail)
                binding.rvEmailList.adapter?.notifyDataSetChanged()
                binding.etEmail.setText("")
            }
        }

        binding.btnAddPhone.setOnClickListener {
            val newPhone = binding.etPhone.text.toString()
            if (newPhone != "") {
                phoneNumbers.add(newPhone)
                binding.rvPhoneList.adapter?.notifyDataSetChanged()
                binding.etPhone.setText("")
            }
        }

        // Non Nullable
        user?.let {
            binding.etFirstName.setText(user.first_name)
            binding.etLastName.setText(user.last_name)
            binding.etAddress.setText(user.address)
        }

        binding.btnSave.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val address = binding.etAddress.text.toString()

            val user = User(firstName, lastName, address, emails, phoneNumbers).apply {
                args.user?.id?.let { id = it }
            }

            if (inputCheck(firstName, lastName, address)) {
                if (edit) {
                    viewModel.update(user)
                } else {
                    viewModel.addUser(user)
                }

                val action = EditFragmentDirections.actionEditFragmentToHomeFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun inputCheck(firstName: String, lastName: String, address: String): Boolean {
        return (firstName.isNotEmpty() && lastName.isNotEmpty() && address.isNotEmpty())
    }
}