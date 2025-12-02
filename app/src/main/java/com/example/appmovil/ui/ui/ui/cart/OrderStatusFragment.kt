package com.example.appmovil.ui.ui.ui.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appmovil.R
import com.example.appmovil.data.UserPrefs

class OrderStatusFragment : Fragment() {

    private lateinit var txtUserName: TextView
    private lateinit var txtUserEmail: TextView
    private lateinit var txtUserAddress: TextView
    private lateinit var txtLastPurchase: TextView
    private lateinit var txtProductsBought: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_status, container, false)

        // Vincular views
        txtUserName = view.findViewById(R.id.txtUserName)
        txtUserEmail = view.findViewById(R.id.txtUserEmail)
        txtUserAddress = view.findViewById(R.id.txtUserAddress)
        txtLastPurchase = view.findViewById(R.id.txtLastPurchase)
        txtProductsBought = view.findViewById(R.id.txtProductsBought)

        loadPurchaseInfo(requireContext())

        return view
    }

    private fun loadPurchaseInfo(context: Context) {
        // Obtener usuario real desde UserPrefs
        val name = UserPrefs.getUserName(context)
        val email = UserPrefs.getUserEmail(context)
        val address = UserPrefs.getUserAddress(context) // Asegúrate de que exista en UserPrefs

        // Mostrar el usuario logueado
        txtUserName.text = "Usuario: $name"
        txtUserEmail.text = "Correo: $email"
        txtUserAddress.text = "Dirección: $address"

        // Obtener lista de compras
        val purchases = PurchasePrefs.getPurchases(context)

        if (purchases.isEmpty()) {
            txtLastPurchase.text = "Último total pagado: -"
            txtProductsBought.text = "Productos comprados: -"
            return
        }

        // Última compra
        val last = purchases.last()

        // Mostrar total pagado
        txtLastPurchase.text = "Último total pagado: $${String.format("%.2f", last.total)}"

        // Lista de productos
        val productList = last.items.joinToString("\n") {
            "- ${it.product.name} x${it.quantity}"
        }

        txtProductsBought.text = "Productos comprados:\n$productList"
    }
}
