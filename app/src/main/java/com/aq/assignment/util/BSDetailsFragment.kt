package com.aq.assignment.util

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aq.assignment.R
import com.aq.assignment.databinding.DetailsFragmentBinding
import com.aq.assignment.model.UserData
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * Created by AQUIB RASHID SHAIKH on 02-05-2023.
 */



class BSDetailsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DetailsFragmentBinding
    var userData:UserData? = null

    companion object {
        private const val ARG_DETAILS = "details"
        fun newInstance(details: UserData): BSDetailsFragment {
            val fragment = BSDetailsFragment()
            val args = Bundle()
            args.putSerializable(ARG_DETAILS, details)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userData = it.getSerializable(ARG_DETAILS) as UserData
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)
        Glide.with(requireActivity())
            .load(userData?.url)
            .placeholder(R.drawable.user)
            .into(binding.imgAvatar)
        binding.txtName.text = "${userData?.title}"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheet = view.parent as View
        bottomSheet.backgroundTintMode = PorterDuff.Mode.CLEAR
        bottomSheet.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)
    }
}
