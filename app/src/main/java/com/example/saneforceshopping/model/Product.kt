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


class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        // Items are the same if their unique identifiers (e.g., name or ID) are the same
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        // Items have the same content if all their properties are the same
        return oldItem == newItem
    }
}

data class RequestBody (
    @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()
)

data class Data (

    @SerializedName("product_code"   ) var productCode   : String? = null,
    @SerializedName("product_name"   ) var productName   : String? = null,
    @SerializedName("Product_Qty"    ) var ProductQty    : Int?    = null,
    @SerializedName("Rate"           ) var Rate          : Int?    = null,
    @SerializedName("Product_Amount" ) var ProductAmount : Int?    = null

)
