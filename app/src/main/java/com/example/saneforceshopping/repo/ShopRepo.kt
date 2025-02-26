package com.example.saneforceshopping.repo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.saneforceshopping.model.Product
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopRepo {

    private var _productLiveData = MutableLiveData<ArrayList<Product>>()
    val productLiveData: LiveData<ArrayList<Product>> get() = _productLiveData
    var context: Context? = null

     fun loadData(context: Context) {
         this.context = context
         if (!_productLiveData.isInitialized)
             fetchProducts()

    }
    fun getData():  LiveData<ArrayList<Product>> {
        return productLiveData
    }
    fun updateData(updatedList: ArrayList<Product>) {
        _productLiveData.value = updatedList
    }

    private fun fetchProducts() {
        val call = Retrofit.api.getProducts()
        call.enqueue(object : Callback<ArrayList<Product>> {
            override fun onResponse(call: Call<ArrayList<Product>>, response: Response<ArrayList<Product>>) {
                if (response.isSuccessful) {
                    // Handle the successful response
                    val products = response.body()
                    Log.d("ShopRepo", "Products: $products")

                    _productLiveData.postValue( response.body())

                    products?.forEach { p ->
                        Log.d("Product", "${p.name}: ${p.name}, id: ${p.id}")
                    }

                } else {
                    Log.e("ShopRepo", "Failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })
    }

   /* fun removeItem(pos: Int) {

        val currentList = productLiveData.value ?: ArrayList()

        currentList.removeAt(pos)

        productLiveData.value = currentList
    }*/

    fun saveAll() {

        val call = Retrofit.api.saveProducts(productLiveData.value!!)
        call.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Toast.makeText(context,response.body().toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }


        })

    }
}