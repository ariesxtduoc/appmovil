package com.example.appmovil.ui.theme.ui.cart


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovil.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartAdapter

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
                cartViewModel.updateQuantity(requireContext(), item.product.id, item.quantity + 1)
            },
            onDecrease = { item ->
                if (item.quantity > 1) {
                    cartViewModel.updateQuantity(requireContext(), item.product.id, item.quantity - 1)
                }
            },
            onDelete = { item ->
                cartViewModel.removeItem(requireContext(), item.product.id)
                Toast.makeText(requireContext(), "Producto eliminado", Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerCart.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCart.adapter = adapter

        cartViewModel.loadCart(requireContext())

        cartViewModel.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
            binding.tvTotal.text = "Total: $${cartViewModel.getTotal()}"
        }
    }
}
