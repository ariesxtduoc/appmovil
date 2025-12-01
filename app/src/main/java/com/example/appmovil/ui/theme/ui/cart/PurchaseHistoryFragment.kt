package com.example.appmovil.ui.theme.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovil.databinding.FragmentPurchaseHistoryBinding

class PurchaseHistoryFragment : Fragment() {

    private var _binding: FragmentPurchaseHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var historyAdapter: PurchaseHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPurchaseHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener historial de compras desde SharedPreferences
        val purchases = PurchasePrefs.getPurchases(requireContext()).reversed()

        // Configurar RecyclerView
        historyAdapter = PurchaseHistoryAdapter(purchases)
        binding.recyclerHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }

        // Mostrar u ocultar mensaje cuando no hay compras
        binding.tvNoPurchases.visibility = if (purchases.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
