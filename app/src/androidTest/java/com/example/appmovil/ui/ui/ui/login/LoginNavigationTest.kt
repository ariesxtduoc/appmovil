package com.example.appmovil.ui.ui.ui.login

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.appmovil.R
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class LoginNavigationTest {

    @Test
    fun clickGoRegister_navegaARegisterFragment() {

        // Mock del NavController
        val navController = mockk<NavController>(relaxed = true)

        // Lanzar el fragmento en un contenedor de prueba
        val scenario = launchFragmentInContainer<LoginFragment>(
            themeResId = R.style.Theme_Appmovil
        )

        // Inyectar el NavController en el fragmento
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Hacer click en el TextView para ir al registro
        scenario.onFragment { fragment ->
            val textView = fragment.requireView().findViewById<android.widget.TextView>(R.id.tv_go_register)
            textView.performClick()
        }

        // Verificar navegación
        verify {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}
