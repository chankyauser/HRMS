package com.aq.lovelocal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aq.lovelocal.databinding.FrgCategoryBinding

class FrgCategory : Fragment() {
    private lateinit var binding: FrgCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }
}