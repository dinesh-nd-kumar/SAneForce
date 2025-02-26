package com.example.saneforceshopping.others

import com.example.saneforceshopping.model.Data
import com.example.saneforceshopping.model.Product

object ProductConverter {

    fun convertProductListToDataList(products: ArrayList<Product>): List<Data> {
        return products.map { product ->
            val quantity = product.quantity.toIntOrNull() ?: 0
            val rate = product.rate
            val productAmount = quantity * rate

            Data(
                productCode = product.id,
                productName = product.name,
                ProductQty = quantity,
                Rate = rate,
                ProductAmount = productAmount
            )
        }
    }
}
