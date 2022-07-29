package com.example.ed_currencyconverter.Presentation.MainScreen.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import coil.compose.rememberAsyncImagePainter
import com.example.ed_currencyconverter.Presentation.MainScreen.MainPageEvents
import com.example.ed_currencyconverter.Presentation.MainScreen.MainPageViewModel
import com.example.ed_currencyconverter.Utils.Constant
import com.example.ed_currencyconverter.Utils.TextSizeUtil
import com.example.ed_currencyconverter.ui.theme.Purple700

@Composable
fun AmountField(viewModel: MainPageViewModel){

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)) {

        Text(
            fontWeight = FontWeight.SemiBold,
            text = "EXCHANGE CURRENCY",
            fontSize = TextSizeUtil(textType = "SUB_TITLE"),
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        OutlinedTextField(
            value =viewModel.amount,
            onValueChange = { viewModel.onEvent(MainPageEvents.onAmountChange(it)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Selling Amount")}
        )
    }


}


@Composable
fun CurrencyDropDowns(viewModel: MainPageViewModel){

    var isSellingSelectionExpanded by rememberSaveable { mutableStateOf(false) }
    var isBuyingSelectionExpanded by rememberSaveable { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        Box(modifier = Modifier.weight(1f)) {
            Column() {
                Text(
                    text = "SELL",
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedButton(onClick = { isSellingSelectionExpanded = true }) {
                    Image(
                        painter = rememberAsyncImagePainter(Constant.IMAGE_API+viewModel.sellingCountryCode),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = viewModel.sellingCurrency,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(if (isSellingSelectionExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        "contentDescription",
                        Modifier.clickable { isSellingSelectionExpanded = !isSellingSelectionExpanded })
                }

                // Create a drop-down menu with list of cities,
                // when clicked, set the Text Field text as the city selected
                DropdownMenu(
                    expanded = isSellingSelectionExpanded,
                    onDismissRequest = { isSellingSelectionExpanded = false },
                ) {
                    Constant.CURRENCY_CODES.forEach { label ->
                        DropdownMenuItem(onClick = {
                            isSellingSelectionExpanded = false
                            viewModel.onEvent(MainPageEvents.onSelectSell(label.currencyCode,label.countryCode))
                        }) {
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = label.currencyCode + " / " + label.countryName
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        Box(modifier = Modifier.weight(1f)) {

            Column() {

                Text(
                    text =  "BUY",
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedButton(onClick = { isBuyingSelectionExpanded = true }) {
                    Image(
                        painter = rememberAsyncImagePainter(Constant.IMAGE_API+viewModel.buyingCountryCode),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = viewModel.buyingCurrency,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(if (isBuyingSelectionExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,"contentDescription",
                        Modifier.clickable { isBuyingSelectionExpanded = !isBuyingSelectionExpanded })
                }

                DropdownMenu(
                    expanded = isBuyingSelectionExpanded,
                    onDismissRequest = { isBuyingSelectionExpanded = false }
                ) {
                    Constant.CURRENCY_CODES.forEach { label ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.onEvent(MainPageEvents.onSelectBuy(label.currencyCode,label.countryCode))
                                isBuyingSelectionExpanded = false
                            }) {
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = label.currencyCode + " / " + label.countryName
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun ExchangeButton(viewModel: MainPageViewModel){
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        Button( modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.onEvent(MainPageEvents.onClickConvertBtn)
            }) {
            Text(text = "EXCHANGE")
        }
        Spacer(modifier = Modifier.padding(vertical = 3.dp))
        Text(
            fontWeight = FontWeight.Light,
            text = "Please note: The first five and every 10th transaction are free of charge, others may subject for additional fee.",
            fontSize = TextSizeUtil(textType = "ITEM_TEXT_H4"),
        )
    }
}
