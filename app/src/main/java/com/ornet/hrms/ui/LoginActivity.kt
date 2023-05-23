package com.ornet.hrms.ui

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ornet.hrms.BaseActivity
import com.ornet.hrms.R
import com.ornet.hrms.databinding.ActivityLoginBinding
import com.ornet.hrms.helper.Status
import com.ornet.hrms.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var vm:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        vm = ViewModelProvider(this)[MainViewModel::class.java]
        progress = ProgressDialog(this)
        binding.loginBtn.setOnClickListener {
            validate()
        }
        vm.data.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    progress.show()
                }

                Status.SUCCESS -> {
                    progress.dismiss()
                    if (it.data?.error == false) {
                        Toasty.success(applicationContext, it.data.message).show()
                        startActivity(Intent(applicationContext,DashboardActivity::class.java))
                    } else {
                        Toasty.error(applicationContext, it.data!!.message).show()
                    }
                }

                Status.ERROR -> {
                    progress.dismiss()
                    it.message?.let { it1 ->
                        Toasty.error(
                            this,
                            it1, Toasty.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }

    }

    fun validate(){
        if (binding.phone.text.toString().isEmpty()){
            showAlert("Wrong", "Enter mobile number!", this)
        } else if (binding.phone.text.toString().length < 10){
            showAlert("Wrong", "Enter valid mobile number!", this)
        } else if (binding.password.text.toString().isEmpty()){
            showAlert("Wrong", "Enter Password!", this)
        } else {
            vm.doLogin(binding.phone.text.toString().trim(),binding.password.text.toString())
        }
    }


    companion object{
        fun showAlert(title: String?, message: String?, context: Context?) {
            AlertDialog.Builder(context!!)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", null)
                .show()
        }
    }
}