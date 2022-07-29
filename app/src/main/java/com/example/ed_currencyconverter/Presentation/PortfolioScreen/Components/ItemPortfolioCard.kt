package com.example.ed_currencyconverter.Presentation.PortfolioScreen.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.CurrencyAccount
import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.TransactionRecords
import com.example.ed_currencyconverter.R
import com.example.ed_currencyconverter.Utils.Constant
import com.example.ed_currencyconverter.Utils.TextSizeUtil
import com.example.ed_currencyconverter.ui.theme.Purple700
import java.util.*


@Composable
fun ItemPortfolioCard(data:CurrencyAccount) {
    Card(
        modifier = Modifier
            .fillMaxWidth().padding(vertical = 3.dp),
        shape = RoundedCornerShape(3.dp),
        elevation = 2.dp,
    ) {
            Row( modifier = Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(1f)) {
                    Row() {
                        Image(
                            painter = rememberAsyncImagePainter(Constant.IMAGE_API+data.countryCode),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                        )

                            Column() {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    text =data.currencyName.toString(),
                                    fontSize = TextSizeUtil(textType = "ITEM_TEXT_H4"),
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    fontWeight = FontWeight.Light,
                                    text = "Account Balance:",
                                    textAlign = TextAlign.Center,
                                    fontSize = TextSizeUtil(textType = "ITEM_TEXT_H4"),
                                )

                        }
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier.weight(1f)) {
                   Column() {
                       Text(
                           modifier = Modifier.fillMaxWidth(),
                           fontWeight = FontWeight.Bold,
                           textAlign = TextAlign.Center,
                           color = Purple700,
                           text =  Currency.getInstance(data.code).getSymbol()+" "+data.balance,
                           fontSize = TextSizeUtil(textType = "ITEM_TEXT_H2"),
                       )

                   }
                }
            }

    }



}
