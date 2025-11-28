package com.example.appmovil.ui.theme.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovil.R
import com.example.appmovil.databinding.FragmentHomeBinding
import com.example.appmovil.ui.theme.domain.model.ProductsData
import com.example.appmovil.ui.theme.ui.adapter.ProductAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val productAdapter by lazy {
        ProductAdapter { product ->
            Toast.makeText(context, "Agregado al carrito: ${product.name}", Toast.LENGTH_SHORT).show()
            Log.d("HomeFragment", "Producto agregado: ${product.name}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNavigateBack.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        setupRecyclerView()
        loadProducts()
    }

    private fun setupRecyclerView() {
        binding.rvProductList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }

    /**
     * Lista REAL de productos HuertoHogar
     */
    private fun loadProducts() {
        productAdapter.submitList(ProductsData.productList)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
