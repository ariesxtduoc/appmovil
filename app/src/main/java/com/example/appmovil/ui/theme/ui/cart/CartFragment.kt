package com.example.appmovil.ui.theme.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovil.R
import com.example.appmovil.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartAdapter

    // 🔹 ID del usuario actual (ajústalo según tu sesión o ViewModel)
    private val currentUserId = "usuario123"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CartAdapter(
            onIncrease = { item ->
                cartViewModel.updateQuantity(
                    requireContext(),
                    item.product.id,
                    item.quantity + 1,
                    currentUserId
                )
            },
            onDecrease = { item ->
                if (item.quantity > 1) {
                    cartViewModel.updateQuantity(
                        requireContext(),
                        item.product.id,
                        item.quantity - 1,
                        currentUserId
                    )
                }
            },
            onDelete = { item ->
                cartViewModel.removeItem(requireContext(), item.product.id, currentUserId)
                Toast.makeText(requireContext(), "Producto eliminado", Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerCart.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCart.adapter = adapter

        // Cargar carrito del usuario actual
        cartViewModel.loadCart(requireContext(), currentUserId)

        cartViewModel.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
            binding.tvTotal.text = "Total: $${cartViewModel.getTotal(currentUserId)}"
        }

        // 🔥 BOTÓN PAGAR
        binding.btnPay.setOnClickListener {

            val items = cartViewModel.cartItems.value ?: emptyList()
            val total = cartViewModel.getTotal(currentUserId)

            if (items.isEmpty()) {
                Toast.makeText(requireContext(), "El carrito está vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✔ Registrar la compra en historial
            val purchase = Purchase(
                userId = currentUserId,
                items = items,
                total = total
            )
            PurchasePrefs.savePurchase(requireContext(), purchase)

            // ✔ Vaciar carrito del usuario
            CartPrefs.clearCart(requireContext(), currentUserId)
            cartViewModel.loadCart(requireContext(), currentUserId)

            Toast.makeText(requireContext(), "Compra realizada", Toast.LENGTH_SHORT).show()

            // ✔ Navegar al resumen
            findNavController().navigate(R.id.action_cartFragment_to_orderSummaryFragment)
        }
    }
}
