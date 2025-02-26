package com.example.saneforceshopping.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.saneforceshopping.repo.ShopRepo
import kotlinx.coroutines.launch

class ShopViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ShopRepo()


    fun loadData() {
        viewModelScope.launch{
            repo.loadData(getApplication())
        }
    }
    fun getProductLiveData() : LiveData<ArrayList<Product>> {
        return repo.getData()
    }



    fun saveAll(list : ArrayList<Product>){
        viewModelScope.launch {
            repo.saveAll(list)
        }
    }

}