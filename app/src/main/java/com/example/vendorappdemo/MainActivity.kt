package com.example.vendorappdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.vendorappdemo.databinding.ActivityMainBinding

import com.example.vendorappdemo.models.ProfileFieldsResponse
import com.example.vendorappdemo.viewmodel.ProfileFieldViewModel
import android.app.ProgressDialog

import android.view.View


import android.widget.ArrayAdapter


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ProfileFieldViewModel
    lateinit var dataBinding: ActivityMainBinding
    lateinit var progressBar: ProgressDialog
    var listVersionAndroid: MutableList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


/*
        dataBinding.genderSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                i: Int,
                l: Long,
            ) {
                val country: String =
                    dataBinding.genderSpinner.getItemAtPosition( dataBinding.genderSpinner.getSelectedItemPosition())
                        .toString()
                Toast.makeText(applicationContext, country, Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                // DO Nothing here
            }
        })
*/

        initViewModel()
        fetchData()


    }

    private fun fetchData() {
        /* viewModel.getProfileFieldsData(profileFieldRequest =
         ProfileFieldRequest("rNRccT7K6NVaZ6wgvsuLEUMow22IPYqp", 1))*/
        progressBar = ProgressDialog.show(this, "", "Loading...")

        viewModel.getProfileFieldsData()

        viewModel.setProfileDetailsLiveData.observe(this, Observer {
            if (it != null) {

                if (it.data.success == true) {

                    progressBar.isShowing()



                    dataBinding.publicNametv.text =
                        it.data.vendor_attributes.generalInformation[0].field_name
                    dataBinding.nametv.text =
                        it.data.vendor_attributes.generalInformation[1].field_name
                    dataBinding.profilepicTv.text =
                        it.data.vendor_attributes.generalInformation[3].field_name
                    dataBinding.gendertv.text =
                        it.data.vendor_attributes.generalInformation[2].field_name
                    dataBinding.emailtv.text =
                        it.data.vendor_attributes.generalInformation[4].field_name
                    dataBinding.contacttv.text =
                        it.data.vendor_attributes.generalInformation[5].field_name
                    (dataBinding.etFirstName as TextView).text =
                        it.data.vendor_attributes.generalInformation[0].saved_value
                    (dataBinding.etLastName as TextView).text =
                        it.data.vendor_attributes.generalInformation[1].saved_value
                    //  (dataBinding.genderSpinner as Spinner).text= it.data.vendor_attributes.generalInformation[1].saved_value
                    (dataBinding.etEmailName as TextView).text =
                        it.data.vendor_attributes.generalInformation[4].saved_value
                    (dataBinding.etContactNumber as TextView).text =
                        it.data.vendor_attributes.generalInformation[5].saved_value
                    Glide.with(this)
                        .load(it.data.vendor_attributes.generalInformation[3].saved_value)
                        .into(dataBinding.profilepicIv)


                    //company info
                    dataBinding.companyNametv.text =
                        it.data.vendor_attributes.companyInformation[0].field_name
                    dataBinding.abouttv.text =
                        it.data.vendor_attributes.companyInformation[1].field_name
                    dataBinding.companylogoTv.text =
                        it.data.vendor_attributes.companyInformation[2].field_name
                    dataBinding.companybannerTv.text =
                        it.data.vendor_attributes.companyInformation[3].field_name
                    dataBinding.companyaddresstv.text =
                        it.data.vendor_attributes.companyInformation[4].field_name
                    (dataBinding.etCompanyName as TextView).text =
                        it.data.vendor_attributes.companyInformation[0].saved_value
                    (dataBinding.etAbout as TextView).text =
                        it.data.vendor_attributes.companyInformation[1].saved_value
                    (dataBinding.etCompanyaddress as TextView).text =
                        it.data.vendor_attributes.companyInformation[4].saved_value
                    Glide.with(this)
                        .load(it.data.vendor_attributes.companyInformation[2].saved_value)
                        .into(dataBinding.companylogoIv)
                    Glide.with(this)
                        .load(it.data.vendor_attributes.companyInformation[3].saved_value)
                        .into(dataBinding.companybannerIv)


                    //support info
                    dataBinding.supportnumbertv.text =
                        it.data.vendor_attributes.supportInformation[0].field_name
                    dataBinding.supportemailtv.text =
                        it.data.vendor_attributes.supportInformation[1].field_name
                    dataBinding.facebookidTv.text =
                        it.data.vendor_attributes.supportInformation[2].field_name
                    dataBinding.twitteridTv.text =
                        it.data.vendor_attributes.supportInformation[3].field_name
                    (dataBinding.etSupportnumber as TextView).text =
                        it.data.vendor_attributes.supportInformation[0].saved_value
                    (dataBinding.etSupportemail as TextView).text =
                        it.data.vendor_attributes.supportInformation[1].saved_value
                    (dataBinding.etFacebookid as TextView).text =
                        it.data.vendor_attributes.supportInformation[2].saved_value
                    (dataBinding.etTwitterid as TextView).text =
                        it.data.vendor_attributes.supportInformation[3].saved_value


                    //seo info
                    dataBinding.metakeywordtv.text =
                        it.data.vendor_attributes.sEOInformation[0].field_name
                    dataBinding.metadescriptionTv.text =
                        it.data.vendor_attributes.sEOInformation[1].field_name
                    (dataBinding.etMetakeyword as TextView).text =
                        it.data.vendor_attributes.sEOInformation[0].saved_value
                    (dataBinding.etMetadescription as TextView).text =
                        it.data.vendor_attributes.sEOInformation[1].saved_value

                    //address info
                    dataBinding.aaddresstv.text =
                        it.data.vendor_attributes.addressInformation[0].field_name
                    dataBinding.cityTv.text =
                        it.data.vendor_attributes.addressInformation[1].field_name
                    dataBinding.postalcodeTv.text =
                        it.data.vendor_attributes.addressInformation[2].field_name
                    dataBinding.statetv.text =
                        it.data.vendor_attributes.addressInformation[3].field_name
                    dataBinding.countrytv.text =
                        it.data.vendor_attributes.addressInformation[4].field_name
                    (dataBinding.etAaddress as TextView).text =
                        it.data.vendor_attributes.addressInformation[0].saved_value
                    (dataBinding.etCity as TextView).text =
                        it.data.vendor_attributes.addressInformation[1].saved_value
                    (dataBinding.etPostalcode as TextView).text =
                        it.data.vendor_attributes.addressInformation[2].saved_value


                    val arrayAdapterGender =
                        ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                            it.data.vendor_attributes.generalInformation[2].options)


                   /* for (customer in it.data.vendor_attributes.generalInformation[2].options) {
                        if (customer.label.equals("label")) {

                            Toast.makeText(this,
                                "key is " + it.data.success.toString(),
                                Toast.LENGTH_LONG).show()
                        }
                    }*/

                    dataBinding.genderSpinner.adapter = arrayAdapterGender


                    val arrayAdapterState =
                        ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                            it.data.vendor_attributes.addressInformation[3].options)

                    dataBinding.stateSpinner.adapter = arrayAdapterState

                    val arrayAdapterCountry =
                        ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                            it.data.vendor_attributes.addressInformation[4].options)

                    dataBinding.countrySpinner.adapter = arrayAdapterCountry


                    dataBinding.layoutallLl.setVisibility(View.VISIBLE);
                    progressBar.dismiss();
                } else {
                    Toast.makeText(this,
                        "key is " + it.data.success.toString(),
                        Toast.LENGTH_LONG).show()
                }


            } else {
                Toast.makeText(this,
                    "no data",
                    Toast.LENGTH_LONG).show()
            }
        })


    }

    private fun initViewModel() {

        viewModel = ViewModelProvider(this).get(ProfileFieldViewModel::class.java)

        viewModel.getProfileFieldsDataObserver().observe(this, Observer<ProfileFieldsResponse?> {
            if (it == null) {

                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()

            }
        })
    }


    fun brosweBtn(view: android.view.View) {

    }
}