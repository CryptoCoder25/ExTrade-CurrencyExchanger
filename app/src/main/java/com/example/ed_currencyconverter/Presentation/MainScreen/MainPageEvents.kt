package com.example.ed_currencyconverter.Presentation.MainScreen

sealed class MainPageEvents {

    data class  onAmountChange(val amount: String): MainPageEvents()

    data class onSelectSell(val sellingCurrency: String, val sellingCountryCode: String): MainPageEvents()

    data class onSelectBuy(val buyingCurrency: String, val buyingCountryCode: String): MainPageEvents()

    object onClickConvertBtn: MainPageEvents()

    data class onClickAppBarItem(val itemId: String): MainPageEvents()
}
