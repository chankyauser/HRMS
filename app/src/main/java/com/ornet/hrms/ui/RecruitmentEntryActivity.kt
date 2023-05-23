package com.ornet.hrms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.ornet.hrms.R
import com.ornet.hrms.databinding.ActivityRecruitmentEntryBinding

class RecruitmentEntryActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecruitmentEntryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recruitment_entry)
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