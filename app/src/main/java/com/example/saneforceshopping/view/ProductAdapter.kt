package com.example.saneforceshopping.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.saneforceshopping.databinding.RowItemBinding
import com.example.saneforceshopping.model.Product
import java.util.ArrayList

class ProductAdapter(val clickListener: ItemClickListener, var productList: MutableList<Product>)
    : RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            RowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false))
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val p = productList[position]
        holder.bindData(p,position)
//        holder.binding.product = p
        /*

        holder.binding.increaseBtn.setOnClickListener {
            var currentQuantity =  holder.binding.quantityTV.text.toString().toIntOrNull()?:0
            currentQuantity++
            holder.binding.quantityTV.setText(currentQuantity.toString())

        }*/

        /*holder.binding.nameTV.text = p.name
        holder.binding.quantityTV.setText(p.quantity)
        holder.binding.priceTV.setText(p.rate.toString())
        holder.binding.amountTV.text = "${p.rate.toInt().times(p.quantity.toInt())}"
        holder.binding.deleteBtn.setOnClickListener {
            clickListener.onDeleteClicked(p,position)
        }
        holder.binding.increaseBtn.setOnClickListener {
            var currentQuantity =  holder.binding.quantityTV.text.toString().toIntOrNull()?:0
            currentQuantity++
            holder.binding.quantityTV.setText(currentQuantity.toString())

        }
        holder.binding.decreaseBtn.setOnClickListener {
            var currentQuantity =  holder.binding.quantityTV.text.toString().toIntOrNull()?:0
            if (currentQuantity > 0){
                currentQuantity--
                holder.binding.quantityTV.setText(currentQuantity.toString())
            }
        }

        holder.binding.quantityTV.doOnTextChanged { text, start, before, count ->
            if (!text.isNullOrEmpty() && text.toString().toInt() != holder.binding.quantityTV.text.toString().toInt()) {
                clickListener.onItemChanged(p.copy(quantity = text.toString()),position)
                holder.binding.amountTV.text = "${p.rate.toInt().times(p.quantity.toInt())}"
            }

        }*/



    }

    inner class ItemViewHolder(val binding:RowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(p: Product,pos:Int){
            binding.apply {
                nameTV.text = p.name
                quantityTV.setText(p.quantity)
                priceTV.setText(p.rate.toString())
                amountTV.text = "${(p.rate).times(p.quantity.toInt())}"
                increaseBtn.setOnClickListener{
                    p.apply {
                        quantity = "${quantity.toInt() + 1}".also { quantityTV.setText(it) }
                        updateamount(this,pos)
                    }
                }
                quantityTV.doOnTextChanged { text, start, before, count ->
                    if (!text.isNullOrEmpty()) {
                        updateamount(p,pos)
                    }
                }
                priceTV.doOnTextChanged { text, start, before, count ->
                    if (!text.isNullOrEmpty()) {
                        p.rate = text.toString().toInt()
                        updateamount(p,pos)
                    }
                }
                decreaseBtn.setOnClickListener{
                    p.apply {
                        if (quantity.toInt() > 0) {
                            quantity = "${quantity.toInt() - 1}".also { quantityTV.setText(it) }
                            updateamount(this,pos)
                        }
                    }
                }
                deleteBtn.setOnClickListener {
                    clickListener.onDeleteClicked(p,pos)

                }
            }
        }
        fun updateamount(p: Product,pos: Int) {
            binding.amountTV.text = (p.rate).times(p.quantity.toInt()).toString()
            clickListener.onItemChanged(p, pos)

        }

    }
     interface ItemClickListener{
         fun onDeleteClicked(p: Product, pos :Int)
         fun onItemChanged(p: Product, pos :Int)
    }


}