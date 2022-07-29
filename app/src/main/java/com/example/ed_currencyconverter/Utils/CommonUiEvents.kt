package com.example.ed_currencyconverter.Utils

sealed class CommonUiEvents {

    object PopBackStack :CommonUiEvents()
    object CloseApp :CommonUiEvents()
    object LoadingProcess :CommonUiEvents()

    data class Navigate(val route: String): CommonUiEvents()

    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): CommonUiEvents()

    data class ShowToastMessage(
        val message: String
    ): CommonUiEvents()

    data class ShowDialog(
        val transactionType: String,
        val message: String
    ): CommonUiEvents()

}