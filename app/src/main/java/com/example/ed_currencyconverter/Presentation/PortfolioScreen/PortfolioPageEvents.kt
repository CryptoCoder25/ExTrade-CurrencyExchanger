package com.example.ed_currencyconverter.Presentation.PortfolioScreen

import com.example.ed_currencyconverter.Presentation.MainScreen.MainPageEvents
import com.example.ed_currencyconverter.Presentation.TrasactionListsScreen.TransactionListPageEvents

sealed class PortfolioPageEvents{

    data class onClickAppBarItem(val itemId: String): PortfolioPageEvents()

    object onClickOpenAddFundDialog: PortfolioPageEvents()

    object onClickAddFunds: PortfolioPageEvents()

    data class onSelectCurrencyType(val currencyCode: String): PortfolioPageEvents()

    data class  onAmountChange(val amount: String): PortfolioPageEvents()
}
