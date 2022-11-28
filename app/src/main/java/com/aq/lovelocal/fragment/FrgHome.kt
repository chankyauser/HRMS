package com.aq.lovelocal.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.aq.lovelocal.R
import com.aq.lovelocal.adapter.CategoryAdapter
import com.aq.lovelocal.databinding.FrgHomeBinding
import com.aq.lovelocal.model.CategoryModel
import com.aq.lovelocal.util.onItemClick
import com.aq.lovelocal.viewmodel.MainViewModel
import com.google.android.gms.location.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class FrgHome : Fragment(), View.OnClickListener, onItemClick{
    private lateinit var binding: FrgHomeBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    val categoryList = ArrayList<CategoryModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgHomeBinding.inflate(inflater, container, false)
        mainViewModel = ViewModelProvider(activity!!).get(MainViewModel::class.java)
        locationSettings()
        setupCategory()
        dayGreetings()
        setViewListener()
        return binding.root
    }

    private fun setupCategory() {
        categoryList.clear()
        categoryList.add(
            CategoryModel(
                7814,
                "Fruits & Vegetables",
                R.drawable.ic_fruits_n_vegies
            )
        )
        categoryList.add(CategoryModel(7828, "Meat & Sea Food", R.drawable.ic_meat_n_fish))
        categoryList.add(
            CategoryModel(
                7827,
                "Health & Medicines",
                R.drawable.ic_health_medicine
            )
        )
        categoryList.add(CategoryModel(7818, "Dairy", R.drawable.ic_dairy))
        categoryList.add(
            CategoryModel(
                7915,
                "Chocolate & Snacks",
                R.drawable.ic_chips_n_chocolates
            )
        )
        categoryList.add(CategoryModel(7821, "Personal Care", R.drawable.ic_beauty_care))
        binding.rvCateGory.setHasFixedSize(true)
        binding.rvCateGory.adapter = CategoryAdapter(activity!!, categoryList, this)
    }

    private fun setViewListener() {
        binding.imgSearch.setOnClickListener(this)
    }

    private fun dayGreetings() {
        val date = Date()
        val cal = Calendar.getInstance()
        cal.time = date
        val hour = cal[Calendar.HOUR_OF_DAY]

        //Set greeting

        //Set greeting
        when (hour) {
            in 12..16 -> {
                binding.toolbar.title = getString(R.string.afternoon_greet)
            }
            in 17..20 -> {
                binding.toolbar.title = getString(R.string.evening_greet)
            }
            in 21..23 -> {
                binding.toolbar.title = getString(R.string.night_greet)
            }
            else -> {
                binding.toolbar.title =getString(R.string.morning_greet)
            }
        }
    }

    private fun locationSettings() {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(activity!!)
        locationRequest = LocationRequest.create()
        locationRequest.interval = 2000
        locationRequest.fastestInterval = 1000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                for (location in locationResult.locations) {
                    getAddress(location)
                }
            }
        }
    }
    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    override fun onResume() {
        super.onResume()
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                startLocationUpdates()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                startLocationUpdates()
            }
            else ->
                openSettings()
        }
    }
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", activity?.packageName, null)
        intent.data = uri
        startActivityForResult(intent, 1001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001) {
            if (resultCode == Activity.RESULT_OK) {
                startLocationUpdates()
            }
        }
    }

    private fun getAddress(currentLatlng: Location) {
        val geocoder = context?.let { Geocoder(it, Locale.getDefault()) }

        val addresses: MutableList<Address>? =
            currentLatlng?.latitude?.let {
                currentLatlng?.longitude?.let { it1 ->
                    geocoder?.getFromLocation(
                        it,
                        it1, 1
                    )
                }
            }
        if (!addresses.isNullOrEmpty()) {
            val address: String = addresses[0].getAddressLine(0)
            val city: String = addresses[0].locality
            val state: String = addresses[0].adminArea
            val zip: String = addresses[0].postalCode
            val country: String = addresses[0].countryName
            binding.txtAddress.text = "$address, $city"
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            binding.imgSearch.id ->{
                Navigation.findNavController(view).navigate(R.id.frgSearch)
            }
        }
    }

    override fun onCLick(id: Int) {
        val navArgs = Bundle()
        if (categoryList.size > id) {
            navArgs.run {
                putInt("cat_id", categoryList[id].id)
                putString("cat_name", categoryList[id].name)
            }
            Navigation.findNavController(binding.root).navigate(R.id.frgProduct, navArgs)
        }
    }


}
