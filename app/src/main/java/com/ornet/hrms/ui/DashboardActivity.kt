package com.ornet.hrms.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ornet.hrms.R
import com.ornet.hrms.databinding.ActivityDashboardBinding


class DashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        binding.back.setOnClickListener { finish() }
        clickHandles()
    }

    fun clickHandles(){
        binding.enquiryForm.setOnClickListener { startActivity(Intent(applicationContext, NewEnquiry::class.java)) }
        binding.assignedCalls.setOnClickListener { startActivity(Intent(applicationContext, EnquiryAssignedList::class.java)) }
    }
}