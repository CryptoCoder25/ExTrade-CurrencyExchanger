package com.example.ed_currencyconverter.Presentation.TrasactionListsScreen

import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.TransactionRecords


data class TransactionListStates(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val records: List<TransactionRecords> = emptyList(),
    val isError: Boolean = false
)