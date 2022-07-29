package com.example.ed_currencyconverter.Presentation.PortfolioScreen

import com.example.ed_currencyconverter.Presentation.MainScreen.MainPageViewModel


import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ed_currencyconverter.Presentation.MainScreen.Components.*
import com.example.ed_currencyconverter.Presentation.PortfolioScreen.Components.AccountBalanceContainer
import com.example.ed_currencyconverter.Presentation.PortfolioScreen.Components.BottomAppBarPortfolio
import com.example.ed_currencyconverter.Presentation.PortfolioScreen.Components.PortfolioListContainer
import com.example.ed_currencyconverter.Presentation.TrasactionListsScreen.TransactionsListViewModel
import com.example.ed_currencyconverter.Presentation.UiUtils.AddFundsDialog
import com.example.ed_currencyconverter.Presentation.UiUtils.LoadingDialog
import com.example.ed_currencyconverter.Presentation.UiUtils.LoadingEffect
import com.example.ed_currencyconverter.Presentation.UiUtils.StatusDialog
import com.example.ed_currencyconverter.R
import com.example.ed_currencyconverter.Utils.AppBottomItem
import com.example.ed_currencyconverter.Utils.CommonUiEvents
import com.example.ed_currencyconverter.Utils.Constant
import com.example.ed_currencyconverter.Utils.Constant.CURRENCY_CODES
import com.example.ed_currencyconverter.Utils.TextSizeUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PortfolioPage(
    onNavigate: (CommonUiEvents.Navigate) -> Unit,
    currentScreen: MutableState<AppBottomItem>,
    activity: Activity?,
){
    var openSuccessDialog by remember { mutableStateOf(false) }
    var openAddFundsDialog by remember { mutableStateOf(false) }
    var openLoadingDialog by remember { mutableStateOf(false) }

    var dialoyType by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val viewModel: PortfolioViewModel =  hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val state =   viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->

            when (event) {
                is CommonUiEvents.Navigate -> {
                    onNavigate(event)
                }
                is CommonUiEvents.CloseApp -> {
                    activity?.let { activity.finish() }
                }
                is CommonUiEvents.ShowDialog ->{
                    openLoadingDialog = false
                    dialoyType = event.transactionType
                    message = event.message

                    if(event.transactionType.equals(Constant.ADD_FUNDS)){
                        openSuccessDialog = false
                        openAddFundsDialog = true
                    }else{
                        openSuccessDialog = true
                        openAddFundsDialog = false
                    }

                }  is CommonUiEvents.LoadingProcess ->{
                     openAddFundsDialog = false
                     openLoadingDialog = true

                     }
                is CommonUiEvents.CloseApp ->  Unit

                else -> Unit
            }

        }
    }


    if (openAddFundsDialog) {
        AddFundsDialog(viewModel) {
            openAddFundsDialog = false
        }

    }

    if (openLoadingDialog) {
        LoadingDialog {
            openLoadingDialog = false
        }

    }

    if (openSuccessDialog) {
        StatusDialog(dialoyType,message) {
            openSuccessDialog = false
        }

    }



        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            scaffoldState = scaffoldState,
            bottomBar = {
                BottomAppBarPortfolio(currentScreenId = currentScreen.value.id) {
                    currentScreen.value = it
                }
            }
        ) {
            Column() {
                AccountBalanceContainer(viewModel)
                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                if(state.isLoading){

                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center)
                    {
                        LoadingEffect()
                    }
                } else if(state.isError || state.isEmpty){

                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        Column(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {

                            Image( painter = painterResource(R.drawable.no_records),
                                modifier = Modifier.size(100.dp),
                                contentDescription = "")

                            Text(
                                text = "No record found!",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Gray,

                                )
                        }
                    }

                } else {

                PortfolioListContainer(state.records)

            }

        }

    }

}


