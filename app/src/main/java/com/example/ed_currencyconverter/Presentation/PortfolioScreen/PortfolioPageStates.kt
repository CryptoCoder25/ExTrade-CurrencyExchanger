package com.example.ed_currencyconverter.Presentation.PortfolioScreen

import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.CurrencyAccount
import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.TransactionRecords

data class PortfolioPageStates(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val records: List<CurrencyAccount> = emptyList(),
    val isError: Boolean = false
)
