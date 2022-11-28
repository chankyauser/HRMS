package com.aq.lovelocal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aq.lovelocal.adapter.OrderAdapter
import com.aq.lovelocal.adapter.SearchedProductAdapter
import com.aq.lovelocal.databinding.FrgCartBinding
import com.aq.lovelocal.helper.Status
import com.aq.lovelocal.model.cart.CartData
import com.aq.lovelocal.util.DividerItemDecoration
import com.aq.lovelocal.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrgCart : Fragment() {
    private lateinit var binding: FrgCartBinding
    private lateinit var  mainViewModel: MainViewModel
    private lateinit var adapter: OrderAdapter
    private val orderList = ArrayList<CartData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgCartBinding.inflate(inflater, container, false)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleView()
        return binding.root
    }

    private fun handleView() {
        mainViewModel.getCartData()
        adapter = OrderAdapter(activity!!, orderList)
        binding.rvCart.adapter= adapter
        binding.rvCart.addItemDecoration(DividerItemDecoration(5,context!!))

        mainViewModel.cartData.observe(viewLifecycleOwner){
            when(it.status) {
                Status.LOADING -> {
                    if(!binding.progress.isVisible)
                        binding.progress.visibility =View.VISIBLE
                }
                Status.SUCCESS -> {
                    if(binding.progress.isVisible)
                        binding.progress.visibility =View.GONE
                    orderList.clear()
                    if (binding.noInternet.isVisible)
                        binding.noInternet.visibility = View.GONE

                    it.data?.let { it1 ->
                        if (!it1.data.isNullOrEmpty()){
                            orderList.clear()
                            orderList.addAll(it1.data)
                            adapter.notifyDataSetChanged()
                            if (binding.imgSearchNotFound.isVisible)
                                binding.imgSearchNotFound.isVisible = false

                        }else{
                            if (!binding.imgSearchNotFound.isVisible)
                                binding.imgSearchNotFound.isVisible = true
                        }

                    }
                }
                Status.ERROR -> {
                    if (binding.progress.isVisible)
                        binding.progress.visibility = View.GONE
                    binding.noInternet.visibility = View.VISIBLE
                    Toast.makeText(
                        context,
                        "Something went wrong, Please Try again later",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }


    }
}