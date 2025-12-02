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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class OrderStatusFragment : Fragment(), OnMapReadyCallback {

    private lateinit var txtUserName: TextView
    private lateinit var txtUserEmail: TextView
    private lateinit var txtUserAddress: TextView
    private lateinit var txtLastPurchase: TextView
    private lateinit var txtProductsBought: TextView

    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_status, container, false)

        // === Views ===
        txtUserName = view.findViewById(R.id.txtUserName)
        txtUserEmail = view.findViewById(R.id.txtUserEmail)
        txtUserAddress = view.findViewById(R.id.txtUserAddress)
        txtLastPurchase = view.findViewById(R.id.txtLastPurchase)
        txtProductsBought = view.findViewById(R.id.txtProductsBought)

        loadPurchaseInfo(requireContext())


        val mapFragment = childFragmentManager.findFragmentById(R.id.mapContainer)
                as SupportMapFragment?

        mapFragment?.getMapAsync(this)

        return view
    }


    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // 📍 Ubicación de ejemplo -> Plaza de Armas, Santiago
        val exampleLocation = LatLng(-33.4372, -70.6506)

        map.addMarker(
            MarkerOptions()
                .position(exampleLocation)
                .title("Ubicación de entrega")
        )

        // Zoom al mapa
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(exampleLocation, 14f))
    }


    private fun loadPurchaseInfo(context: Context) {

        val name = UserPrefs.getUserName(context)
        val email = UserPrefs.getUserEmail(context)
        val address = UserPrefs.getUserAddress(context)

        txtUserName.text = "Usuario: $name"
        txtUserEmail.text = "Correo: $email"
        txtUserAddress.text = "Dirección: $address"

        val purchases = PurchasePrefs.getPurchases(context)

        if (purchases.isEmpty()) {
            txtLastPurchase.text = "Último total pagado: -"
            txtProductsBought.text = "Productos comprados: -"
            return
        }

        val last = purchases.last()

        txtLastPurchase.text = "Último total pagado: $${String.format("%.2f", last.total)}"

        val productList = last.items.joinToString("\n") {
            "- ${it.product.name} x${it.quantity}"
        }

        txtProductsBought.text = "Productos comprados:\n$productList"
    }
}
