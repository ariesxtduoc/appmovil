package com.example.appmovil.ui.ui.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovil.databinding.FragmentOrderSummaryBinding

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

        // 🔙 Volver atrás
        binding.btnBackOrderSummary.setOnClickListener {
            findNavController().popBackStack()
        }

        // 🧾 Obtener la última compra guardada
        val lastPurchase = PurchasePrefs.getPurchases(requireContext()).lastOrNull()

        if (lastPurchase != null) {

            summaryAdapter = SummaryAdapter(lastPurchase.items)

            binding.recyclerSummary.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = summaryAdapter
            }

            binding.summaryTotal.text = "Total pagado: $${lastPurchase.total}"

        } else {
            binding.summaryTotal.text = "No hay compras registradas."
        }

        // ⭐ FINALIZAR → Ir al estado del pedido usando Safe Args
        binding.btnFinalizar.setOnClickListener {
            val action = OrderSummaryFragmentDirections
                .actionOrderSummaryFragmentToOrderStatusFragment()

            findNavController().navigate(action)
        }
    }
}
