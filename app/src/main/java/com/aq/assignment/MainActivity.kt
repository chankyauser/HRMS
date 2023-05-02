package com.aq.assignment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.aq.assignment.adapter.ListAdapter
import com.aq.assignment.databinding.ActivityMainBinding
import com.aq.assignment.helper.Status
import com.aq.assignment.model.UserData
import com.aq.assignment.util.BSDetailsFragment
import com.aq.assignment.util.DividerItemDecoration
import com.aq.assignment.viewmodel.MainViewModel
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
        handleView()
        handleObserveAble()
    }

    private fun handleObserveAble() {
        mainViewModel.data.observe(this){
            when(it.status) {
                Status.LOADING -> {
                    if(!binding.progress.isVisible)
                        binding.progress.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    if(binding.progress.isVisible)
                        binding.progress.visibility = View.GONE
                }
                Status.ERROR -> {
                    if (binding.progress.isVisible)
                        binding.progress.visibility = View.GONE
                    if(userData.isEmpty()) {
                        binding.noInternet.visibility = View.VISIBLE
                        Toast.makeText(
                            applicationContext,
                            "Something went wrong, Please Try again later",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            mainViewModel.fetchStudentData()

            mainViewModel.userFinalList.observe(this) { users ->
                if (!users.isNullOrEmpty()) {
                    userData.addAll(users)
                    adapter.notifyDataSetChanged()
                } else {
                    if (!binding.noInternet.isVisible && userData.isEmpty())
                        binding.noInternet.isVisible = true
                }
            }


        }

    }

    private fun handleView() {
        mainViewModel.getUserData()
        adapter = ListAdapter(this,userData){
            BSDetailsFragment.newInstance(it).show(supportFragmentManager,"Data")
        }
        binding.rvUserList.adapter = adapter
        binding.rvUserList.addItemDecoration(DividerItemDecoration(5,applicationContext))
    }
}