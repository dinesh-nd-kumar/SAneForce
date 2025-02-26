package com.example.saneforceshopping.model

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_code")
    val id: String,

    @SerializedName("product_unit")
    val productUnit: String,

    @SerializedName("product_name")
    val name: String,

    @SerializedName("convQty")
    var quantity: String,

    @SerializedName("Rate")
    var rate: Int = 0

    ){

}




data class RequestBody (
    @SerializedName("data" ) var data : List<Data> = listOf()
)

data class Data (

    @SerializedName("product_code"   ) var productCode   : String? = null,
    @SerializedName("product_name"   ) var productName   : String? = null,
    @SerializedName("Product_Qty"    ) var ProductQty    : Int?    = null,
    @SerializedName("Rate"           ) var Rate          : Int?    = null,
    @SerializedName("Product_Amount" ) var ProductAmount : Int?    = null

)
