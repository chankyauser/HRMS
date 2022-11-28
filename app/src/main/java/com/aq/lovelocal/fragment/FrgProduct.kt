package com.aq.lovelocal.fragment

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aq.lovelocal.adapter.ProductAdapter
import com.aq.lovelocal.adapter.SearchedProductAdapter
import com.aq.lovelocal.databinding.FrgProductBinding
import com.aq.lovelocal.helper.Status
import com.aq.lovelocal.util.DividerItemDecoration
import com.aq.lovelocal.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrgProduct : Fragment() {
    private lateinit var binding: FrgProductBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: ProductAdapter
    private val productList = ArrayList<com.aq.lovelocal.model.product.Data>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgProductBinding.inflate(inflater, container, false)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleView()
        handleObserveAble()
        return binding.root
    }

    private fun handleObserveAble() {
        mainViewModel.productData.observe(viewLifecycleOwner){
            when(it.status) {
                Status.LOADING -> {
                    if(!binding.progress.isVisible)
                        binding.progress.visibility =View.VISIBLE
                }
                Status.SUCCESS -> {
                    if(binding.progress.isVisible)
                        binding.progress.visibility =View.GONE
                    productList.clear()
                    if (binding.noInternet.isVisible)
                        binding.noInternet.visibility = View.GONE

                    it.data?.let { it1 ->
                        if (!it1.data.isNullOrEmpty()){
                            productList.addAll(it1.data)
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

    private fun handleView() {
       binding.imgBack.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.txtCatName.text = arguments?.getString("cat_name")
        adapter = ProductAdapter(context!!,productList)
        binding.rvProduct.adapter = adapter
        binding.rvProduct.addItemDecoration(DividerItemDecoration(5,context!!))

        arguments?.getInt("cat_id",0)?.let { mainViewModel.getProductByCate(it) }
    }
}