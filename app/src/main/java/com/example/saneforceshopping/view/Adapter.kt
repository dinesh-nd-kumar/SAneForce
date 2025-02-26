package com.example.saneforceshopping.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.saneforceshopping.databinding.RowItemBinding
import com.example.saneforceshopping.model.Product
import com.example.saneforceshopping.model.ProductDiffCallback

class Adapter(
    private val clickListener: ItemClickListener
) : ListAdapter<Product, Adapter.ItemViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = getItem(position)
        holder.bindData(product, position)
    }

    inner class ItemViewHolder(private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(p: Product, pos: Int) {
            binding.apply {
                // Bind data to views
                nameTV.text = p.name
                quantityTV.setText(p.quantity)
                priceTV.setText(p.rate.toString())
                amountTV.text = "${p.rate!!.toInt().times(p.quantity.toInt())}"

                // Increase quantity
                increaseBtn.setOnClickListener {
                    p.apply {
                        quantity = "${quantity.toInt() + 1}".also { quantityTV.setText(it) }
                        updateAmount(this, pos)
                    }
                }

                // Update quantity when text changes
                quantityTV.doOnTextChanged { text, start, before, count ->
                    if (!text.isNullOrEmpty()) {
                        p.quantity = text.toString()
                        updateAmount(p, pos)
                    }
                }

                // Update rate when text changes
                priceTV.doOnTextChanged { text, start, before, count ->
                    if (!text.isNullOrEmpty()) {
                        p.rate = text.toString().toInt()
                        updateAmount(p, pos)
                    }
                }

                // Decrease quantity
                decreaseBtn.setOnClickListener {
                    p.apply {
                        if (quantity.toInt() > 0) {
                            quantity = "${quantity.toInt() - 1}".also { quantityTV.setText(it) }
                            updateAmount(this, pos)
                        }
                    }
                }

                // Delete item
                deleteBtn.setOnClickListener {
                    clickListener.onDeleteClicked(p, pos)
                }
            }
        }

        private fun updateAmount(p: Product, pos: Int) {
            binding.amountTV.text = p.rate!!.toInt().times(p.quantity.toInt()).toString()
            clickListener.onItemChanged(p, pos)
        }
    }

    interface ItemClickListener {
        fun onDeleteClicked(p: Product, pos: Int)
        fun onItemChanged(p: Product, pos: Int)
    }
}