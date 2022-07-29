package com.example.ed_currencyconverter.Presentation.MainScreen.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.ed_currencyconverter.Presentation.MainScreen.MainPageViewModel
import com.example.ed_currencyconverter.Utils.Constant
import com.example.ed_currencyconverter.Utils.TextSizeUtil
import com.example.ed_currencyconverter.ui.theme.Purple700
import com.example.ed_currencyconverter.ui.theme.Teal200
import java.util.*


@Composable
fun BalanceContainer(viewModel: MainPageViewModel){


    Column(modifier = Modifier.background(Purple700).padding(vertical = 30.dp, horizontal = 20.dp)) {

        Text(
            fontWeight = FontWeight.SemiBold,
            text = "CURRENCY BALANCE",
            fontSize = TextSizeUtil(textType = "SUB_TITLE"),
            color = Color.White
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(3.dp),
                    elevation = 1.dp,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Row() {
                            Box(modifier = Modifier.weight(1f)) {
                                Image(
                                    painter = rememberAsyncImagePainter(Constant.IMAGE_API+viewModel.sellingCountryCode),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(25.dp)
                                        .clip(CircleShape)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(modifier = Modifier.weight(1f)) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.End,
                                    text = viewModel.sellingCurrency,
                                    fontSize = TextSizeUtil(textType = "ITEM_TEXT_H2"),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.SemiBold,
                            text = Currency.getInstance(viewModel.sellingCurrency).getSymbol()+viewModel.accountBalance,
                            textAlign = TextAlign.Center,
                            fontSize = TextSizeUtil(textType = "ITEM_TEXT_H1"),
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Light,
                            text = "Available Balance",
                            textAlign = TextAlign.Center,
                            fontSize = TextSizeUtil(textType = "ITEM_TEXT_H4"),
                        )
                        Spacer(modifier = Modifier.height(5.dp))


                    }


                }

            }


            Spacer(modifier = Modifier.width(15.dp))

            Box(modifier = Modifier.weight(1f)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(3.dp),
                    elevation = 1.dp,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Row() {
                            Box(modifier = Modifier.weight(1f)) {
                                Image(
                                    painter = rememberAsyncImagePainter(Constant.IMAGE_API+viewModel.buyingCountryCode),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(25.dp)
                                        .clip(CircleShape)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(modifier = Modifier.weight(1f)) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.End,
                                    text = viewModel.buyingCurrency,
                                    fontSize = TextSizeUtil(textType = "ITEM_TEXT_H2"),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.SemiBold,
                            text =  Currency.getInstance(viewModel.buyingCurrency).getSymbol()+viewModel.targetCurrencyBalance,
                            textAlign = TextAlign.Center,
                            fontSize = TextSizeUtil(textType = "ITEM_TEXT_H1"),
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Light,
                            text = "Available Balance",
                            textAlign = TextAlign.Center,
                            fontSize = TextSizeUtil(textType = "ITEM_TEXT_H4"),
                        )
                        Spacer(modifier = Modifier.height(5.dp))


                    }


                }

            }


        }
    }

}