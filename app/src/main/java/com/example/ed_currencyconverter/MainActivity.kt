package com.example.ed_currencyconverter

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ed_currencyconverter.Presentation.MainScreen.MainPage
import com.example.ed_currencyconverter.Presentation.PortfolioScreen.PortfolioPage
import com.example.ed_currencyconverter.Presentation.TrasactionListsScreen.TransactionListPage
import com.example.ed_currencyconverter.Utils.AppBottomItem
import com.example.ed_currencyconverter.Utils.Constant
import com.example.ed_currencyconverter.ui.theme.EDCurrencyConverterTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentScreen= mutableStateOf<AppBottomItem>(AppBottomItem.Home)

        setContent {
            EDCurrencyConverterTheme {
                val activity = (LocalContext.current as? Activity)
                AppContent(currentScreen,activity)
            }
        }
    }

    override fun onBackPressed() {
        Toast.makeText(applicationContext,"Pls use the navigation menu..", Toast.LENGTH_SHORT).show()
    }
}


@Composable
fun AppContent(currentScreen : MutableState<AppBottomItem>, activity: Activity?){

    Scaffold() {

        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Constant.MainScreen
        )
        {
            composable(Constant.MainScreen) {
                MainPage(
                    onNavigate = {navController.navigate(it.route)},
                    currentScreen,
                    activity
                )
            }
            composable(Constant.PortfolioScreen) {
                PortfolioPage(
                    onNavigate = { navController.navigate(it.route) },
                    currentScreen,
                    activity
                )
            }

            composable(Constant.TransactionListScreen) {
               TransactionListPage(
                    onNavigate = {navController.navigate(it.route)},
                   activity,
                   currentScreen
                )
            }


        }
    }

}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EDCurrencyConverterTheme {

    }
}


