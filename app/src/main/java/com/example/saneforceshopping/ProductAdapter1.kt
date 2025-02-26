package com.example.saneforceshopping

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.saneforceshopping.databinding.RowItemBinding
import com.example.saneforceshopping.model.Product

class ProductAdapter1(val clickListener: ItemClickListener, var productList: ArrayList<Product>?)
    : RecyclerView.Adapter<ProductAdapter1.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            RowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false))
    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemCount(): Int {
        if (productList == null)
            return 0
        return productList!!.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name = productList?.get(position)
        if (name != null) {
            holder.bindData(name)
        }

    }

    inner class ItemViewHolder(val binding:RowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(p: Product?){
            binding.apply {
                nameTV.text = p?.name
                quantityTV.setText(p?.quantity)
                priceTV.setText(p?.rate.toString())
                amountTV.text = "${p?.rate?.toInt()?.times(p.quantity.toInt())}"
                increaseBtn.setOnClickListener{
                    p?.apply {
                        quantity = "${quantity.toInt() + 1}".also { quantityTV.setText(it) }
                        updateamount(this)
                    }
                }
                quantityTV.doOnTextChanged { text, start, before, count ->
                    if (!text.isNullOrEmpty()) {
                        updateamount(p!!)
                    }
                }
                priceTV.doOnTextChanged { text, start, before, count ->
                    if (!text.isNullOrEmpty()) {
                        p?.rate = text.toString().toInt()
                        updateamount(p!!)
                    }
                }
                decreaseBtn.setOnClickListener{
                    p?.apply {
                        if (quantity.toInt() > 0) {
                                quantity = "${quantity.toInt() - 1}".also { quantityTV.setText(it) }
                                updateamount(this)
                            }
                    }
                }
                deleteBtn.setOnClickListener {
                    clickListener.onDeleteClicked(p!!,adapterPosition)

                }
            }
        }
        fun updateamount(p: Product) {
            productList?.set(adapterPosition, p)
            binding.amountTV.text = p.rate?.toInt()?.times(p.quantity.toInt()).toString()

        }

    }
     interface ItemClickListener{
         fun onDeleteClicked(p: Product, pos :Int)
         fun onItemChanged(p: Product, pos :Int)
    }


}