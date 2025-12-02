package com.example.appmovil.ui.ui.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appmovil.R
import com.example.appmovil.databinding.FragmentLoginBinding

// ⭐ IMPORT NECESARIO PARA GUARDAR DATOS DEL USUARIO
import com.example.appmovil.data.UserPrefs

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val prefs by lazy {
        requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cargar logo desde URL
        val url = binding.imgLogo.tag.toString()
        Glide.with(requireContext())
            .load(url)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(binding.imgLogo)

        // Botón LOGIN
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            attemptLogin(email, password)
        }

        // Ir al REGISTRO
        binding.tvGoRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    // ⭐ FUNCIÓN CORREGIDA
    private fun attemptLogin(email: String, password: String) {
        val savedEmail = prefs.getString("user_email", null)
        val savedPassword = prefs.getString("user_password", null)
        val savedName = prefs.getString("user_name", "Usuario") ?: "Usuario"
        val savedAddress = prefs.getString("user_address", "") ?: "" // ✅ Dirección guardada o vacía

        when {
            email.isEmpty() || password.isEmpty() -> {
                Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }

            email == savedEmail && password == savedPassword -> {

                // ⭐ GUARDAR DATOS DEL USUARIO LOGUEADO
                UserPrefs.saveUser(requireContext(), savedName, savedEmail!!, savedAddress)

                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }

            else -> {
                Toast.makeText(context, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
