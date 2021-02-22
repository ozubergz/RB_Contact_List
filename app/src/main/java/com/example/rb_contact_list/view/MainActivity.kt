package com.example.rb_contact_list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.rb_contact_list.R
import com.example.rb_contact_list.viewmodel.ViewModel

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    private val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.allUsers.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
        })
    }
}