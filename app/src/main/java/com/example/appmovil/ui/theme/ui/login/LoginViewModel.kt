package com.example.appmovil.ui.theme.ui.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel que maneja la lógica de la pantalla de inicio de sesión.
 * NOTA: En una aplicación real, aquí se manejarían las llamadas a la API de autenticación.
 */
class LoginViewModel : ViewModel() {

    // 1. Estado de los campos de entrada (MutableLiveData)
    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    // 2. Estado del resultado del login (true = éxito, false = error, null = esperando/inicial)
    private val _loginStatus = MutableLiveData<Boolean?>(null)
    val loginStatus: LiveData<Boolean?> = _loginStatus

    /**
     * Intenta autenticar al usuario.
     * Por ahora, usa credenciales de prueba: Email="test@appmovil.com", Password="123456"
     */
    fun attemptLogin() {
        val currentEmail = email.value.orEmpty()
        val currentPassword = password.value.orEmpty()

        // Simulación de credenciales válidas
        val isValid = currentEmail == "test@appmovil.com" && currentPassword == "123456"

        _loginStatus.value = isValid

        // NOTA: Reiniciar el estado del login a 'null' para permitir nuevas interacciones.
        _loginStatus.value = null
    }
}