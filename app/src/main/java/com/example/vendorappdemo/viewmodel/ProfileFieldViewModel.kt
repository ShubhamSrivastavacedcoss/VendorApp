package com.example.vendorappdemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vendorappdemo.api.EndPointInterface
import com.example.vendorappdemo.api.RetrofitHelper
import com.example.vendorappdemo.models.ProfileFieldRequest
import com.example.vendorappdemo.models.ProfileFieldsResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import androidx.lifecycle.LiveData
import com.example.vendorappdemo.models.Data
import com.example.vendorappdemo.models.GeneralInformation


class ProfileFieldViewModel : ViewModel() {

    lateinit var setProfileDetailsLiveData: MutableLiveData<ProfileFieldsResponse?>

   
    init {
        setProfileDetailsLiveData = MutableLiveData()

    }

    fun getProfileFieldsDataObserver(): MutableLiveData<ProfileFieldsResponse?> {
        return setProfileDetailsLiveData
    }


    fun getProfileFieldsData(/*profileFieldRequest: ProfileFieldRequest*/) {
        val retrofitHelper = RetrofitHelper.getInstance().create(EndPointInterface::class.java)
        val call = retrofitHelper.getProfileDetails("rNRccT7K6NVaZ6wgvsuLEUMow22IPYqp", 1)

        call.enqueue(object : retrofit2.Callback<ProfileFieldsResponse> {

            override fun onFailure(call: Call<ProfileFieldsResponse>, t: Throwable) {
                setProfileDetailsLiveData.postValue(null)
            }

            override fun onResponse(
                call: Call<ProfileFieldsResponse>, response: Response<ProfileFieldsResponse>,
            ) {
                if (response.isSuccessful) {
                    setProfileDetailsLiveData.postValue(response.body())
                    Log.i("TAG", "onResponse: ")


                } else {
                    setProfileDetailsLiveData.postValue(null)

                }
            }

        })


    }

}


