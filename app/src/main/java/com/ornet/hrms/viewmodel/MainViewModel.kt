package com.ornet.hrms.viewmodel

import androidx.lifecycle.*
import com.ornet.hrms.helper.Resource
import com.ornet.hrms.model.LoginResponse
import com.ornet.hrms.model.UserData
import com.ornet.hrms.repository.MainRepository
import com.ornet.hrms.room.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(
    private val userDao: UserDao,
    private val mainRepository: MainRepository
): ViewModel(){

    var userInfo: LiveData<List<LoginResponse.UserInformations>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchStudentData(){
        viewModelScope.launch {

            userInfo = userDao.getAll()
        }
    }


    private val _userData = MutableLiveData<Resource<LoginResponse>>()
    val data : LiveData<Resource<LoginResponse>>
        get() = _userData

    fun doLogin(mobileNo:String, pass:String)  = CoroutineScope(Dispatchers.IO).launch {
        _userData.postValue(Resource.loading(null))
        try {
            mainRepository.doLogin(mobileNo,pass).let {
                if (it.isSuccessful){
                    _userData.postValue(Resource.success(it.body()))
                    userDao.deleteAll()
                    it.body()?.let { it1 -> userDao.insertAll(it1.UserInformation) }
                }else{
                    _userData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            _userData.postValue(Resource.error(e.message.toString(), null))
        }
    }

}