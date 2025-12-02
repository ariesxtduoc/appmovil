package com.example.appmovil.ui.theme.login


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.appmovil.MainActivity
import com.example.appmovil.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginNavigationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun alPresionarTextoIrARegistro_navegaAlRegistro() {

        activityRule.scenario.onActivity { activity ->

            val navController = TestNavHostController(activity)
            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(
                activity.findViewById(android.R.id.content),
                navController
            )
        }

        // Click en el TextView con ID tv_go_register
        onView(withId(R.id.tv_go_register)).perform(click())

        // Verificamos que llegó a RegisterFragment
        onView(withId(R.id.btn_register))
            .check(matches(isDisplayed()))
    }
}
