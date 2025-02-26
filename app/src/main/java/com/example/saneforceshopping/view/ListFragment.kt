package com.example.saneforceshopping.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.saneforceshopping.ProductAdapter1
import com.example.saneforceshopping.databinding.FragmentListBinding
import com.example.saneforceshopping.model.Product
import com.example.saneforceshopping.model.ShopViewModel


class ListFragment : Fragment(), ProductAdapter1.ItemClickListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var shopViewModel: ShopViewModel? = null

    private lateinit var productAdapter: ProductAdapter1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        shopViewModel = ViewModelProvider(requireActivity()).get(ShopViewModel::class.java)
        shopViewModel!!.loadData()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentListBinding.inflate(inflater,container,false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        shopViewModel!!.getProductLiveData().observe(viewLifecycleOwner) {
            setRecycler(it)
            Toast.makeText(requireContext(), "updated", Toast.LENGTH_SHORT).show()
        }
        binding.saveBtn.setOnClickListener {
            shopViewModel?.saveAll()
        }

    }
    private fun initRecycler(){
        binding.itemRv.apply {
            productAdapter = ProductAdapter1(this@ListFragment, arrayListOf())
            adapter = productAdapter
            addItemDecoration(DividerItemDecoration( context,DividerItemDecoration.VERTICAL))

        }
    }
    private fun setRecycler(list:ArrayList<Product>){
        productAdapter.productList = list
        productAdapter.notifyDataSetChanged()
    }



    override fun onDeleteClicked(p: Product, pos: Int) {
        Toast.makeText(requireContext(), "$pos", Toast.LENGTH_LONG).show()
        productAdapter.productList?.removeAt(pos)
//        shopViewModel?.removeItem(pos)
        productAdapter.notifyItemRemoved(pos)
    }

    override fun onItemChanged(p: Product, pos: Int) {
        productAdapter.productList?.set(pos, p)
//        shopViewModel?.updateItem(pos,p)
        productAdapter.notifyItemChanged(pos)
    }


}