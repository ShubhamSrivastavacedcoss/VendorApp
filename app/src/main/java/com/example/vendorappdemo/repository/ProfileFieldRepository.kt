package com.example.vendorappdemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vendorappdemo.api.EndPointInterface
import com.example.vendorappdemo.models.ProfileFieldRequest
import com.example.vendorappdemo.models.ProfileFieldsResponse
import retrofit2.http.Body

/*
class ProfileFieldRepository(private val endPointInterface: EndPointInterface) {

    var profileFiledMutableData= MutableLiveData<ProfileFieldsResponse>()

    val profileField: LiveData<ProfileFieldsResponse>
        get() = profileFiledMutableData


    suspend fun getProfileDetails(profileFieldRequest: ProfileFieldRequest){
        val result= endPointInterface.getProfileDetails(profileFieldRequest)

    }




}*/
