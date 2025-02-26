package com.example.saneforceshopping.repo

import com.example.saneforceshopping.model.Product
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApis {
    @GET("server/native_Db_V13.php?axn=get/taskproducts&divisionCode=258")
     fun getProducts(): Call<ArrayList<Product>>

    @POST("server/native_Db_V13.php?axn=save/taskproddets&divisionCode=258")
     fun saveProducts(@Body data: List<Product>): Call<ResponseBody>


}