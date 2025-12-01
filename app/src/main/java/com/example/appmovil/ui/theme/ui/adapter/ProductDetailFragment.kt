package com.example.appmovil.ui.theme.ui.adapter

import com.example.appmovil.ui.theme.ui.cart.CartPrefs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.appmovil.databinding.FragmentProductDetailBinding
import com.example.appmovil.ui.theme.domain.model.Product
import com.google.gson.Gson

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val args: ProductDetailFragmentArgs by navArgs()

    // Aquí obtienes el ID del usuario actual (ajústalo según tu implementación)
    private val currentUserId = "usuario123" // reemplaza con ViewModel de usuario o sesión

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recibir producto
        val productJson = args.productoJson
        val product = Gson().fromJson(productJson, Product::class.java)

        // Mostrar datos
        binding.txtNombreDetalle.text = product.name
        binding.txtPrecioDetalle.text = "$${product.price}"
        binding.txtDescripcionDetalle.text = product.description

        Glide.with(requireContext())
            .load(product.imageUrl)
            .into(binding.imgProductoDetalle)

        // Botón agregar al carrito
        binding.btnAgregarCarrito.setOnClickListener {
            CartPrefs.addProduct(requireContext(), product, 1, currentUserId)
            Toast.makeText(requireContext(), "Agregado al carrito", Toast.LENGTH_SHORT).show()
        }

        // Botón volver
        binding.btnVolver.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
