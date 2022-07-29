package com.example.ed_currencyconverter.Presentation.PortfolioScreen.Components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.CurrencyAccount
import com.example.ed_currencyconverter.Presentation.MainScreen.MainPageEvents
import com.example.ed_currencyconverter.Presentation.TrasactionListsScreen.Components.ItemTransactionCard
import com.example.ed_currencyconverter.Utils.TextSizeUtil


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortfolioListContainer(currencyList: List<CurrencyAccount>){

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 5.dp)) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(start = 3.dp, end = 3.dp))
        {

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {

                items(currencyList) { currency ->
                    ItemPortfolioCard(currency)
                }
            }



        }
    }

}