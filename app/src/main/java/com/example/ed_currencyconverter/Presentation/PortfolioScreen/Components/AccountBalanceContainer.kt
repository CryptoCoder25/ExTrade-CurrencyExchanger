package com.example.ed_currencyconverter.Presentation.PortfolioScreen.Components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ed_currencyconverter.Presentation.PortfolioScreen.PortfolioPageEvents
import com.example.ed_currencyconverter.Presentation.PortfolioScreen.PortfolioViewModel
import com.example.ed_currencyconverter.Utils.TextSizeUtil
import com.example.ed_currencyconverter.ui.theme.Purple700



@Composable
fun AccountBalanceContainer(viewModel: PortfolioViewModel){

    val viewModel: PortfolioViewModel =  hiltViewModel()

    Column(modifier = Modifier
        .background(Purple700)
        .fillMaxWidth()
        .padding(vertical = 20.dp, horizontal = 20.dp)) {

       Row(modifier = Modifier.fillMaxWidth(1f)) {
           Box(modifier = Modifier.weight(2f)) {
               Text(
                   fontWeight = FontWeight.SemiBold,
                   text = "MAIN ACCOUNT",
                   fontSize = TextSizeUtil(textType = "SUB_TITLE"),
                   color = Color.White
               )
           }
           Box(modifier = Modifier.weight(1f)) {

           }
       }
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
         ) {
            Text(
                fontWeight = FontWeight.SemiBold,
                text = viewModel.diplayBalance,
                fontSize = TextSizeUtil(textType = "MAIN_TITLE"),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Light,
                text = "Available Balance",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = TextSizeUtil(textType = "ITEM_TEXT_H2"),
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                onClick = { viewModel.onEvent(PortfolioPageEvents.onClickOpenAddFundDialog)},
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White,backgroundColor = Purple700),
            ){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Add ,contentDescription = "content description", tint= Color.White)
                   // Spacer(modifier = Modifier.padding(horizontal = 5.dp ))
                    Text( text = "ADD FUNDS" )
                }
               
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }

}

