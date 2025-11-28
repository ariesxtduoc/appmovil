package com.example.appmovil.ui.theme.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appmovil.R
import com.example.appmovil.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

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

        // 🔹 1. Cargar la imagen del logo desde la URL guardada en android:tag del XML
        val url = binding.imgLogo.tag.toString()

        Glide.with(requireContext())
            .load(url)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(binding.imgLogo)


        // 🔹 2. Inputs -> ViewModel
        binding.etEmail.afterTextChanged {
            viewModel.email.value = it
        }

        binding.etPassword.afterTextChanged {
            viewModel.password.value = it
        }

        // 🔹 3. Botón login
        binding.btnLogin.setOnClickListener {
            viewModel.attemptLogin()
        }

        // 🔹 4. Observador del estado de login
        viewModel.loginStatus.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                false -> Toast.makeText(context, "Error: Correo o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
                null -> {} // No hacer nada en el estado inicial
            }
        }
    }

    private fun EditText.afterTextChanged(action: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                action(s.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
