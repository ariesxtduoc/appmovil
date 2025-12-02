package com.example.appmovil.ui.ui.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovil.databinding.FragmentHomeBinding
import com.example.appmovil.ui.ui.domain.model.ProductsData
import com.example.appmovil.ui.ui.ui.adapter.ProductAdapter
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
                val productoJson = Gson().toJson(product)
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

        // Volver atrás
        binding.btnNavigateBack.setOnClickListener {
            findNavController().popBackStack()
        }

        // Ir al carrito
        binding.btnCart.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToCartFragment()
            )
        }

        // Ir al historial de compras
        binding.btnPurchaseHistory.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToPurchaseHistoryFragment()
            )
        }

        // Ir al perfil de usuario
        binding.btnUser.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToProfileFragment()
            )
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
