package com.example.appmovil.ui.theme.ui.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appmovil.R
import com.example.appmovil.databinding.FragmentLoginBinding

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

        // Botón ir a REGISTRO
        binding.tvGoRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun attemptLogin(email: String, password: String) {
        val savedEmail = prefs.getString("user_email", null)
        val savedPassword = prefs.getString("user_password", null)

        when {
            email.isEmpty() || password.isEmpty() -> {
                Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
            email == savedEmail && password == savedPassword -> {
                // Login correcto
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else -> {
                Toast.makeText(context, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun EditText.afterTextChanged(action: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) { action(s.toString()) }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
