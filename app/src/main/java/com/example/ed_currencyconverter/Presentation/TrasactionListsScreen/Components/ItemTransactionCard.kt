package com.example.ed_currencyconverter.Presentation.TrasactionListsScreen.Components

import android.util.Log.d
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.TransactionRecords
import com.example.ed_currencyconverter.Utils.TextSizeUtil
import com.example.ed_currencyconverter.R
import com.example.ed_currencyconverter.Utils.Constant
import com.example.ed_currencyconverter.ui.theme.Teal200


@Composable
fun ItemTransactionCard(record: TransactionRecords) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center) {

                    Image(
                        painter = rememberAsyncImagePainter("https://countryflagsapi.com/png/"+record.fromCountryCode),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(45.dp).clip(CircleShape)
                    )

                }

                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = record.soldAmount.toString()+record.soldCurrency,
                        fontSize = TextSizeUtil(textType = "ITEM_TEXT_H4"),
                        fontWeight = FontWeight.Normal,
                        color = Color.Black

                    )

                }

                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(R.drawable.exchange),
                        contentDescription = "",
                        modifier = Modifier.padding(10.dp)
                    )


                }
                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = record.purchasedValue.toString()+record.purchasedCurrency,
                        fontSize = TextSizeUtil(textType = "ITEM_TEXT_H4"),
                        fontWeight = FontWeight.Normal,
                        color = Color.Black

                    )

                }

                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center) {

                    Image(
                        painter = rememberAsyncImagePainter("https://countryflagsapi.com/png/"+record.toCountryCode),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                    )



                }

            }

            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically) {


                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center) {

                    Text(
                        text = "Fee: ${record.commission}${record.soldCurrency}",
                        fontSize = TextSizeUtil(textType = "ITEM_TEXT_H4"),
                        fontWeight = FontWeight.Light,
                        color = Color.Black

                    )


                }
                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center) {

                    Text(
                        text = "Date: ${record.transactionDate}",
                        fontSize = TextSizeUtil(textType = "ITEM_TEXT_H4"),
                        fontWeight = FontWeight.Light,
                        color = Color.Black

                    )
                }


                }




        }
    }


}
