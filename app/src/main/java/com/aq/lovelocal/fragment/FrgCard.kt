package com.aq.lovelocal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aq.lovelocal.databinding.FrgCardBinding

class FrgCard : Fragment() {
    private lateinit var binding: FrgCardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgCardBinding.inflate(inflater, container, false)
        return binding.root
    }
}