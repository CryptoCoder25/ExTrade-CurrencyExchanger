package com.example.ed_currencyconverter.Presentation.TrasactionListsScreen


import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ed_currencyconverter.Presentation.MainScreen.Components.BottomAppBarMain
import com.example.ed_currencyconverter.Presentation.TrasactionListsScreen.Components.BottomAppBarSubMain
import com.example.ed_currencyconverter.Presentation.TrasactionListsScreen.Components.ItemTransactionCard
import com.example.ed_currencyconverter.Presentation.UiUtils.LoadingEffect
import com.example.ed_currencyconverter.R
import com.example.ed_currencyconverter.Utils.AppBottomItem
import com.example.ed_currencyconverter.Utils.CommonUiEvents
import com.example.ed_currencyconverter.Utils.TextSizeUtil
import com.example.ed_currencyconverter.ui.theme.Purple700
import com.example.ed_currencyconverter.ui.theme.Teal200
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TransactionListPage(
    onNavigate: (CommonUiEvents.Navigate) -> Unit,
    activity: Activity?,
    currentScreen: MutableState<AppBottomItem>,
){
   val viewModel: TransactionsListViewModel =  hiltViewModel()
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

                else -> Unit
            }

        }
    }

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            scaffoldState = scaffoldState,
            bottomBar = {
                BottomAppBarSubMain( currentScreenId = currentScreen.value.id) {
                    currentScreen.value= it
                }
            }
        )
        {
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp)) {

                Card(
                    modifier = Modifier.fillMaxWidth().background(Purple700),
                    elevation = 1.dp) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Purple700)
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "TRANSACTIONS",
                            fontSize = TextSizeUtil(textType = "SUB_TITLE"),
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Image(
                            painter = painterResource(R.drawable.records),
                            contentDescription = "",
                            modifier = Modifier.padding(5.dp))

                    }
                }

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

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 3.dp, end = 3.dp))
                {

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(10.dp)
                    ) {

                        items(state.records) { record ->
                            ItemTransactionCard(record)
                        }
                    }

                } }
            }

        }

    }




