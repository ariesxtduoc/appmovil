package com.example.appmovil.ui.theme.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovil.databinding.FragmentHomeBinding
import com.example.appmovil.ui.theme.ui.adapter.ProductAdapter
import com.example.appmovil.ui.theme.domain.model.Product

class HomeFragment : Fragment() {

    // View Binding: Usado para acceder a las vistas de fragment_home.xml
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Inicialización del adaptador. Pasamos la lógica de lo que pasa al hacer clic.
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
        setupRecyclerView()
        loadSampleData()
    }

    private fun setupRecyclerView() {
        binding.rvProductList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }

    private fun loadSampleData() {
        val sampleProducts = listOf(
            Product(
                id = "1",
                name = "Tomate Chonto Orgánico",
                description = "Sabor dulce y fresco. Ideal para ensaladas o salsas.",
                price = 3.50,
                unit = "Kg",
                imageUrl = "https://placehold.co/600x400/38761D/ffffff?text=TOMATE"
            ),
            Product(
                id = "2",
                name = "Lechuga Romana Fresca",
                description = "Hojas crujientes, de color verde intenso.",
                price = 1.20,
                unit = "Unidad",
                imageUrl = "https://placehold.co/600x400/4F7942/ffffff?text=LECHUGA"
            ),
        )
        productAdapter.submitList(sampleProducts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
