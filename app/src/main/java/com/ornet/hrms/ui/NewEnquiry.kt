package com.ornet.hrms.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ornet.hrms.R
import com.ornet.hrms.databinding.ActivityNewEnquiryBinding
import java.text.SimpleDateFormat
import java.util.Date


class NewEnquiry : AppCompatActivity() {
    lateinit var binding: ActivityNewEnquiryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_enquiry)
        binding.back.setOnClickListener { finish() }

        binding.groupradio.setOnCheckedChangeListener { group, checkedId ->

        }
        val selectedId: Int = binding.groupradio.checkedRadioButtonId

        binding.date.setText(getCurrentDate())
    }
    fun getCurrentDate():String{
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        return currentDate
    }
}