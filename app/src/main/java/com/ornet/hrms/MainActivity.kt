package com.ornet.hrms

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.ornet.hrms.adapter.ListAdapter
import com.ornet.hrms.databinding.ActivityMainBinding
import com.ornet.hrms.helper.Status
import com.ornet.hrms.model.UserData
import com.ornet.hrms.util.BSDetailsFragment
import com.ornet.hrms.util.DividerItemDecoration
import com.ornet.hrms.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ListAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    val userData = ArrayList<UserData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }


}