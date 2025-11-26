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
import com.example.appmovil.ui.theme.domain.model.Product
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

        // Botón volver al login
        binding.btnNavigateBack.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        setupRecyclerView()
        loadRealProducts()
    }

    private fun setupRecyclerView() {
        binding.rvProductList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }

    /**
     * CARGA DE PRODUCTOS REALES DE HUERTOHOGAR
     */
    private fun loadRealProducts() {
        val products = listOf(
            // --- FRUTAS FRESCAS ---
            Product(
                id = "FR001",
                name = "Manzanas Fuji",
                description = "Manzanas crujientes y dulces, cultivadas en el Valle del Maule.",
                price = 1200.0,
                unit = "Kilo",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg"
            ),
            Product(
                id = "FR002",
                name = "Naranjas Valencia",
                description = "Jugosas y ricas en vitamina C. Ideales para jugos frescos.",
                price = 1000.0,
                unit = "Kilo",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg"
            ),
            Product(
                id = "FR003",
                name = "Plátanos Cavendish",
                description = "Plátanos dulces y maduros, ricos en potasio.",
                price = 800.0,
                unit = "Kilo",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/8a/Banana-Single.jpg "
            ),

            // --- VERDURAS ORGÁNICAS ---
            Product(
                id = "VR001",
                name = "Zanahorias Orgánicas",
                description = "Cultivadas sin pesticidas, ricas en vitamina A.",
                price = 900.0,
                unit = "Kilo",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/2/29/Carrots.jpg"
            ),
            Product(
                id = "VR002",
                name = "Espinacas Frescas",
                description = "Perfectas para ensaladas y batidos verdes.",
                price = 700.0,
                unit = "Bolsa 500g",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/1/1f/Spinach_leaves.jpg"
            ),
            Product(
                id = "VR003",
                name = "Pimientos Tricolores",
                description = "Pimientos rojos, amarillos y verdes, ricos en antioxidantes.",
                price = 1500.0,
                unit = "Kilo",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/5/5f/Colored_bell_peppers.jpg"
            ),

            // --- PRODUCTOS ORGÁNICOS ---
            Product(
                id = "PO001",
                name = "Miel Orgánica",
                description = "Miel pura producida por apicultores locales.",
                price = 5000.0,
                unit = "Frasco 500g",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/0/08/Honey_%28white_background%29.jpg"
            ),
            Product(
                id = "PO003",
                name = "Quinua Orgánica",
                description = "Fuente de proteínas vegetales y fibra.",
                price = 3200.0,
                unit = "Bolsa 1kg",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/5/55/Quinoa_white.png"
            ),

            // --- LÁCTEOS ---
            Product(
                id = "PL001",
                name = "Leche Entera",
                description = "Leche fresca proveniente de granjas locales.",
                price = 1200.0,
                unit = "1 Litro",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/0/0c/Milk_glass.jpg"
            )
        )

        productAdapter.submitList(products)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
