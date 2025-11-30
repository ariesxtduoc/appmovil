package com.example.appmovil.ui.theme.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovil.databinding.FragmentOrderSummaryBinding
import com.example.appmovil.ui.theme.ui.cart.PurchasePrefs

class OrderSummaryFragment : Fragment() {

    private lateinit var binding: FragmentOrderSummaryBinding
    private lateinit var summaryAdapter: SummaryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lastPurchase = PurchasePrefs.getPurchases(requireContext()).lastOrNull()

        if (lastPurchase != null) {

            // Adaptador para listar los productos comprados
            summaryAdapter = SummaryAdapter(lastPurchase.items)

            binding.recyclerSummary.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = summaryAdapter
            }

            // Mostrar total
            binding.summaryTotal.text = "Total pagado: $${lastPurchase.total}"

        } else {
            binding.summaryTotal.text = "No hay compras registradas."
        }

        binding.btnFinalizar.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}
