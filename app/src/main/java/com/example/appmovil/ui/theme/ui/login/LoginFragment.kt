package com.example.appmovil.ui.theme.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

        binding.etEmail.afterTextChanged {
            viewModel.email.value = it
        }
        binding.etPassword.afterTextChanged {
            viewModel.password.value = it
        }

        binding.btnLogin.setOnClickListener {
            viewModel.attemptLogin()
        }

        viewModel.loginStatus.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess == true) {

                // ✅ NAVEGACIÓN CORRECTA
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

            } else if (isSuccess == false) {
                Toast.makeText(context, "Error: Email o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun android.widget.EditText.afterTextChanged(action: (String) -> Unit) {
        this.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                action(s.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
