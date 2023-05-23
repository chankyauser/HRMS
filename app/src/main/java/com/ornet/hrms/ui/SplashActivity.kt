package com.ornet.hrms.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.ornet.hrms.R
import com.ornet.hrms.model.LoginResponse
import com.ornet.hrms.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var vm:MainViewModel
    private val userInfo = ArrayList<LoginResponse.UserInformations>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        vm = ViewModelProvider(this)[MainViewModel::class.java]
        vm.fetchStudentData()
        vm.userInfo.observe(this) {list ->
            userInfo.addAll(list)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            CoroutineScope(Dispatchers.IO).launch {
                if (userInfo.isNullOrEmpty()) {
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                    finish()
                }else {
                    startActivity(Intent(applicationContext, DashboardActivity::class.java))
                    finish()
                }
            }
            finish()
        }, 700)

    }

    private fun gotoNextScreen() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}