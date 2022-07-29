package com.example.ed_currencyconverter.Utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class AppBottomItem(
    val id:String,
    val title:String,
    val icon: ImageVector,
){

    object Home: AppBottomItem(Constant.main_page,"Exchange", Icons.Outlined.Home)
    object Portfolio: AppBottomItem(Constant.portfolio_page,"Portfolio", Icons.Outlined.AccountBox)
    object CheckList: AppBottomItem(Constant.sub_page,"Transactions",Icons.Outlined.List)
    object Exit: AppBottomItem(Constant.exit_page,"Exit",Icons.Outlined.ExitToApp)

    object Items{
        val list= listOf(
            Home,Portfolio,CheckList,Exit
        )
    }

}
