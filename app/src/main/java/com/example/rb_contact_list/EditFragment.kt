package com.example.rb_contact_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
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

        val emails : ArrayList<String> = arrayListOf()
        val phoneNumbers : ArrayList<String> = arrayListOf()

        binding.btnAddEmail.setOnClickListener {
            val email = binding.etEmail.text.toString()
            emails.add(email)
            binding.etEmail.setText("")
        }

        binding.btnAddPhone.setOnClickListener {
            val phone = binding.etPhone.text.toString()
            phoneNumbers.add(phone)
            binding.etPhone.setText("")
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
            }
        }
    }

    private fun inputCheck(firstName: String, lastName: String, address: String): Boolean {
        return (firstName.isNotEmpty() && lastName.isNotEmpty() && address.isNotEmpty())
    }
}