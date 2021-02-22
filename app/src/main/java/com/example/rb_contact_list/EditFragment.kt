package com.example.rb_contact_list

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.rb_contact_list.databinding.FragmentEditBinding
import com.example.rb_contact_list.modal.User
import com.example.rb_contact_list.viewmodel.ViewModel

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private val viewModel by activityViewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentEditBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val user = User(firstName, lastName)

            if(inputCheck(firstName, lastName))
                viewModel.addUser(user)
        }
    }

    private fun inputCheck(firstName: String, lastName: String) : Boolean {
        return !(firstName.isEmpty() && lastName.isEmpty())
    }
}