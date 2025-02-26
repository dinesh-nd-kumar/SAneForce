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

   /* fun removeItem(pos :Int)  {
         repo.removeItem(pos)
    }
    fun updateItem(pos: Int, item: Product) {
        repo.getData().value?.set(pos, item)
    }*/

    fun removeItem(pos: Int) {
//        val currentList = repo.getData().value ?: return
        if (pos >= 0 && pos < repo.getData().value?.size!!) {
            repo.getData().value?.removeAt(pos)
//            repo.updateData(updatedList) // Update the LiveData with a new list
        }
    }

    fun updateItem(pos: Int, item: Product) {
//        val currentList = repo.getData().value ?: return
        if (pos >= 0 && pos < repo.getData().value?.size!!) {
            repo.getData().value!![pos]  =  item
//            repo.updateData(updatedList) // Update the LiveData with a new list
        }
    }

    fun saveAll(){
        viewModelScope.launch {
            repo.saveAll()
        }
    }

}