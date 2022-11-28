package com.aq.lovelocal.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aq.lovelocal.adapter.SearchedProductAdapter
import com.aq.lovelocal.databinding.FrgSearchBinding
import com.aq.lovelocal.helper.Status
import com.aq.lovelocal.model.search.Product
import com.aq.lovelocal.util.DividerItemDecoration
import com.aq.lovelocal.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FrgSearch : Fragment() {
    private lateinit var binding: FrgSearchBinding
    private lateinit var  mainViewModel: MainViewModel
    private lateinit var adapter: SearchedProductAdapter
    var searchList = ArrayList<Product>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgSearchBinding.inflate(inflater, container, false)
        ViewCompat.setTranslationZ(binding.root, 100f);
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleView()
        return binding.root
    }

    private fun handleView() {
        adapter = SearchedProductAdapter(context!!,searchList)
        binding.rvSearch.adapter = adapter
        binding.edtSearch.requestFocus()
        binding.rvSearch.addItemDecoration(DividerItemDecoration(5,context!!))
        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (s.length > 3)
                    mainViewModel.searchProduct(s.toString())
                else {
                    searchList.clear()
                    adapter.notifyDataSetChanged()
                }

            }
        })
        mainViewModel.searchData.observe(viewLifecycleOwner){
            when(it.status) {
                Status.LOADING -> {
                    if(!binding.progress.isVisible)
                    binding.progress.visibility =View.VISIBLE
                }
                Status.SUCCESS -> {
                    if(binding.progress.isVisible)
                    binding.progress.visibility =View.GONE
                    searchList.clear()
                    if (binding.noInternet.isVisible)
                        binding.noInternet.visibility = View.GONE

                    it.data?.let { it1 ->
                        if (!it1.data.isNullOrEmpty()){
                            searchList.addAll(it1.data[0].products)
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

    override fun onResume() {
        super.onResume()
        binding.edtSearch.post {
            binding.edtSearch.requestFocus()
            val inputMethodManager: InputMethodManager =
                activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(binding.edtSearch, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}