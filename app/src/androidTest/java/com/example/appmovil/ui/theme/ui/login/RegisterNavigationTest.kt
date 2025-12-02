package com.example.appmovil.ui.theme.ui.login

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
class RegisterNavigationTest {

    @Test
    fun clickBackButton_navegaALoginFragment() {

        val navController = mockk<NavController>(relaxed = true)

        val scenario = launchFragmentInContainer<RegisterFragment>(
            themeResId = R.style.Theme_Appmovil
        )

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Simula clic en botón Volver
        scenario.onFragment { fragment ->
            fragment.requireView()
                .findViewById<android.widget.Button>(R.id.btn_back)
                .performClick()
        }

        // Verifica navegación correcta
        verify {
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}
