package com.example.appmovil.ui.theme.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovil.databinding.FragmentHomeBinding
import com.example.appmovil.ui.theme.domain.model.ProductsData
import com.example.appmovil.ui.theme.ui.adapter.ProductAdapter
import com.google.gson.Gson

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val productAdapter by lazy {
        ProductAdapter(
            onAddToCartClick = { product ->
                Toast.makeText(
                    requireContext(),
                    "Agregado al carrito: ${product.name}",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onItemClick = { product ->
                // Convertimos el producto a JSON
                val productoJson = Gson().toJson(product)

                // Navegamos usando Safe Args (SIN NOMBRE DE PARAMETRO)
                val action = HomeFragmentDirections
                    .actionHomeFragmentToProductDetailFragment(productoJson)

                findNavController().navigate(action)
            }
        )
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

        // Botón volver
        binding.btnNavigateBack.setOnClickListener {
            findNavController().popBackStack()
        }

        setupRecyclerView()
        loadProducts()
    }

    private fun setupRecyclerView() {
        binding.rvProductList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
        }
    }

    private fun loadProducts() {
        productAdapter.submitList(ProductsData.productList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
