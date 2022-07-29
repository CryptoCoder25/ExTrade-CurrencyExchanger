package com.example.ed_currencyconverter.Presentation.TrasactionListsScreen

sealed class TransactionListPageEvents {

    data class onClickAppBarItem(val itemId: String): TransactionListPageEvents()
}

