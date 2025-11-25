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
import com.example.appmovil.R
import com.example.appmovil.databinding.FragmentLoginBinding // Importación CRÍTICA para View Binding

/**
 * Fragmento que maneja la vista de Inicio de Sesión de HuertoHogar.
 * Se encarga de capturar la entrada del usuario y conectarla con el ViewModel.
 */
class LoginFragment : Fragment() {

    // Inicializa el ViewModel que contiene las credenciales de prueba y la lógica.
    private val viewModel: LoginViewModel by viewModels()

    // 1. View Binding: Se usa para acceder a las vistas sin findViewById().
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflamos el layout usando View Binding
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. Conexión de Inputs al ViewModel
        // Cada vez que el texto cambia, actualizamos el MutableLiveData en el ViewModel.
        binding.etEmail.afterTextChanged {
            viewModel.email.value = it // etEmail corresponde al ID et_email del XML
        }

        binding.etPassword.afterTextChanged {
            viewModel.password.value = it // etPassword corresponde al ID et_password del XML
        }

        // 3. Listener del Botón de Login
        binding.btnLogin.setOnClickListener {
            viewModel.attemptLogin()
        }

        // 4. Observador del Estado de Login
        // Cuando el estado cambia en el ViewModel, el Fragment reacciona:
        viewModel.loginStatus.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    // ÉXITO: Navegar al HomeFragment (Catálogo)
                    // Usamos la acción definida en nav_graph.xml
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
                false -> {
                    // FALLO: Mostrar un Toast al usuario
                    Toast.makeText(context, "Error: Correo o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
                }
                null -> {
                    // Estado inicial o reseteado
                }
            }
        }
    }

    /**
     * Función de extensión para simplificar la captura de cambios de texto en EditText.
     */
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
        // Es crucial liberar la referencia del binding para evitar fugas de memoria.
        _binding = null
    }
}