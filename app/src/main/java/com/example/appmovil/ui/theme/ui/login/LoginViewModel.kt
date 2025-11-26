package com.example.appmovil.ui.theme.ui.login // PAQUETE CORREGIDO

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel que maneja el estado de la UI y la lógica de autenticación.
 */
class LoginViewModel : ViewModel() {

    // 1. ESTADO DE LOS INPUTS
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    // 2. ESTADO DE LA NAVEGACIÓN/RESULTADO
    private val _loginStatus = MutableLiveData<Boolean?>()
    val loginStatus: LiveData<Boolean?> = _loginStatus

    // =========================================================================
    // 3. CREDENCIALES VÁLIDAS (Valores de ejemplo)
    private val VALID_EMAIL = "alanbravo.com"
    private val VALID_PASSWORD = "alan"
    // =========================================================================

    /**
     * Intenta autenticar al usuario.
     */
    fun attemptLogin() {
        val inputEmail = email.value ?: return
        val inputPassword = password.value ?: return

        // Lógica de verificación
        if (inputEmail == VALID_EMAIL && inputPassword == VALID_PASSWORD) {
            _loginStatus.value = true // Indica éxito
        } else {
            _loginStatus.value = false // Indica fallo
        }
    }
}