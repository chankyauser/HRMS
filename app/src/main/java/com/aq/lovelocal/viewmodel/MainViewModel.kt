package com.aq.lovelocal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aq.lovelocal.helper.Resource
import com.aq.lovelocal.model.cart.CartResponse
import com.aq.lovelocal.model.product.ProductResponse
import com.aq.lovelocal.model.search.GeneralResponse
import com.aq.lovelocal.model.search.SearchResponse
import com.aq.lovelocal.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel(){
    private val _searchData = MutableLiveData<Resource<SearchResponse>>()
    val searchData : LiveData<Resource<SearchResponse>>
        get() = _searchData

    fun searchProduct(searchQuery: String)  = viewModelScope.launch {
        _searchData.postValue(Resource.loading(null))
        try {
            mainRepository.searchProduct(searchQuery).let {
                if (it.isSuccessful){
                    _searchData.postValue(Resource.success(it.body()))
                }else{
                    _searchData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            _searchData.postValue(Resource.error(e.message.toString(), null))
        }
    }

    private val _productData = MutableLiveData<Resource<ProductResponse>>()
    val productData : LiveData<Resource<ProductResponse>>
        get() = _productData

    fun getProductByCate(catID: Int)  = viewModelScope.launch {
        _productData.postValue(Resource.loading(null))
        try {
            mainRepository.getProductByCate(catID).let {
                if (it.isSuccessful){
                    _productData.postValue(Resource.success(it.body()))
                }else{
                    _productData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            _productData.postValue(Resource.error(e.message.toString(), null))
        }
    }

    private val _cartData = MutableLiveData<Resource<CartResponse>>()
    val cartData : LiveData<Resource<CartResponse>>
        get() = _cartData

    fun getCartData()  = viewModelScope.launch {
        _cartData.postValue(Resource.loading(null))
        try {
            mainRepository.getCartData().let {
                if (it.isSuccessful){
                    _cartData.postValue(Resource.success(it.body()))
                }else{
                    _cartData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            _cartData.postValue(Resource.error(e.message.toString(), null))
        }
    }

}