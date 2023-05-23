package com.ornet.hrms.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ornet.hrms.BaseActivity
import com.ornet.hrms.R
import com.ornet.hrms.adapter.EnquiryPagingAdapter
import com.ornet.hrms.databinding.ActivityEnquiryAssignedListBinding
import com.ornet.hrms.helper.Status
import com.ornet.hrms.repository.EnquiryRepository
import com.ornet.hrms.viewmodel.EnquiryViewModel
import com.ornet.hrms.viewmodel.MainViewModel
import com.ornet.hrms.viewmodelfactory.EnquiryViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class EnquiryAssignedList : BaseActivity() {
    lateinit var binding: ActivityEnquiryAssignedListBinding
    lateinit var enquiryViewModel: EnquiryViewModel
    lateinit var adapter: EnquiryPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enquiry_assigned_list)
        progress = ProgressDialog(this)
        //val viewModelFactory = EnquiryViewModelFactory(repository, )
        enquiryViewModel = ViewModelProvider(this).get(EnquiryViewModel::class.java)
        adapter = EnquiryPagingAdapter()

        binding.rvEnquiry.layoutManager = LinearLayoutManager(this)
        binding.rvEnquiry.setHasFixedSize(true)
        binding.rvEnquiry.adapter = adapter

        enquiryViewModel.list.observe(this, Observer {
            adapter.submitData(lifecycle, it)
            adapter.notifyDataSetChanged()
        })

        toolbarHandle()
    }

    fun toolbarHandle(){
        binding.back.setOnClickListener { finish() }
        binding.backCollapsed.setOnClickListener { finish() }
        binding.appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
                //  Do anything for Expanded State
                verticalOffset == 0 -> {
                    binding.backCollapsed.visibility = View.GONE
                    binding.back.visibility = View.VISIBLE
                }

                kotlin.math.abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                    //  Do anything for Collapse State
                    binding.backCollapsed.visibility = View.VISIBLE
                    binding.back.visibility = View.GONE
                }

                else -> {
                    //  Do anything for Ideal State
                }
            }
        }
    }
}