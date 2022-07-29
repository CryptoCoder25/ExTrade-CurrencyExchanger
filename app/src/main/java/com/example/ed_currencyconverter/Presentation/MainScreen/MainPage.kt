package com.example.ed_currencyconverter.Presentation.MainScreen

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ed_currencyconverter.Presentation.MainScreen.Components.*
import com.example.ed_currencyconverter.Presentation.UiUtils.LoadingDialog
import com.example.ed_currencyconverter.Presentation.UiUtils.StatusDialog
import com.example.ed_currencyconverter.Utils.AppBottomItem
import com.example.ed_currencyconverter.Utils.CommonUiEvents
import com.example.ed_currencyconverter.Utils.Constant
import com.example.ed_currencyconverter.Utils.Constant.CURRENCY_CODES
import com.example.ed_currencyconverter.Utils.TextSizeUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainPage(
    onNavigate: (CommonUiEvents.Navigate) -> Unit,
    currentScreen: MutableState<AppBottomItem>,
    activity: Activity?,
   ){

    var openDialog by remember { mutableStateOf(false) }
    var openLoadingDialog by remember { mutableStateOf(false) }

    var dialoyType by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    val viewModel: MainPageViewModel = hiltViewModel()

    LaunchedEffect(key1 = true){

        viewModel.eventFlow.collectLatest { event ->

            when(event){
                is CommonUiEvents.Navigate  -> {
                    onNavigate(event)
                }
                is CommonUiEvents.CloseApp -> {

                activity?.let { activity.finish() }

                }
                is CommonUiEvents.ShowSnackbar  -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = null)
                }
                is CommonUiEvents.ShowToastMessage  -> {
                   Toast.makeText(context,event.message,Toast.LENGTH_SHORT).show()

                }
                is CommonUiEvents.ShowDialog ->{
                    openLoadingDialog = false
                    openDialog = true
                    dialoyType = event.transactionType
                    message = event.message

                }
                is CommonUiEvents.LoadingProcess ->{
                    openDialog = false
                    openLoadingDialog = true

                }
                is CommonUiEvents.CloseApp ->  Unit

                else -> Unit
            }

        }
    }

    if (openDialog) {
        StatusDialog(dialoyType,message) {
            openDialog = false
        }
       
    }

    if (openLoadingDialog) {
        LoadingDialog {
            openLoadingDialog = false
        }

    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        bottomBar = { BottomAppBarMain( currentScreenId = currentScreen.value.id) { currentScreen.value= it } },
    ) {

        Column() {
            BalanceContainer(viewModel)
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            AmountField(viewModel)
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            CurrencyDropDowns(viewModel)
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            ExchangeButton(viewModel)
        }

    }

}


