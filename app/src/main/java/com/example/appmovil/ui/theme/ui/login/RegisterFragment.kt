package com.example.appmovil.ui.theme.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appmovil.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    // Usamos el mismo nombre de SharedPreferences que en LoginFragment
    private val prefsName = "user_prefs"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔙 Botón VOLVER
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        // 🔹 Botón REGISTRAR
        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            // 1️⃣ Validaciones básicas
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2️⃣ Guardar usuario en SharedPreferences
            val sharedPref = requireActivity().getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("user_name", name)
            editor.putString("user_email", email)
            editor.putString("user_password", password)
            editor.apply()

            Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()

            // 3️⃣ Volver a pantalla de login
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
