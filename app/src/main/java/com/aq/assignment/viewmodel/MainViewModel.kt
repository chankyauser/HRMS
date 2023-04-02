package com.aq.assignment.viewmodel

import androidx.lifecycle.*
import com.aq.assignment.helper.Resource
import com.aq.assignment.model.UserData
import com.aq.assignment.repository.MainRepository
import com.aq.assignment.room.UserDao
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

    var userFinalList: LiveData<List<UserData.Data>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchStudentData(){
        viewModelScope.launch {

            userFinalList = userDao.getAll()
        }
    }


    private val _userData = MutableLiveData<Resource<UserData>>()
    val data : LiveData<Resource<UserData>>
        get() = _userData

    fun getUserData()  = CoroutineScope(Dispatchers.IO).launch {
        _userData.postValue(Resource.loading(null))
        try {
            mainRepository.getData().let {
                if (it.isSuccessful){
                    _userData.postValue(Resource.success(it.body()))
                    userDao.deleteAll()
                    it.body()?.data?.let { it1 -> userDao.insertAll(it1) }
                }else{
                    _userData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            _userData.postValue(Resource.error(e.message.toString(), null))
        }
    }

}