package com.example.ed_currencyconverter.Presentation.UiUtils

import android.text.Layout
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Arrangement.Center

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.ed_currencyconverter.Presentation.MainScreen.MainPageEvents
import com.example.ed_currencyconverter.Presentation.PortfolioScreen.PortfolioPageEvents
import com.example.ed_currencyconverter.Presentation.PortfolioScreen.PortfolioViewModel
import com.example.ed_currencyconverter.R
import com.example.ed_currencyconverter.Utils.Constant
import com.example.ed_currencyconverter.Utils.TextSizeUtil


@Composable
fun  StatusDialog(dialogType: String, message: String, setShowDialog: (Boolean) -> Unit) {
    var title = "";
    var imageIcon: Int = R.drawable.internet_error

    if(dialogType.equals("EXTERNAL_ERROR")){
        title = "NETWORK ERROR"
        imageIcon = R.drawable.internet_error
    }else if (dialogType.equals("INTERNAL_ERROR")){
        title = "TRANSACTION FAILED"
        imageIcon = R.drawable.transaction_error
    }else if (dialogType.equals("SUCCESS")) {
        title = "CONVERSION SUCCESS "
        imageIcon = R.drawable.success
    }else if (dialogType.equals("SUCCESS_ADD_FUNDS")) {
        title = "TRANSACTION SUCCESS "
        imageIcon = R.drawable.success
    } else {
        setShowDialog(false)
    }

    Dialog(
        properties = DialogProperties(dismissOnClickOutside = false),
        onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Text(
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.SemiBold,
                        text =  title,
                        textAlign = TextAlign.Center,
                        fontSize = TextSizeUtil(textType = "ITEM_TEXT_H1"),
                    )
                Spacer(modifier = Modifier.height(15.dp))

                Image( painter = painterResource(imageIcon),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Light,
                    text = message,
                    textAlign = TextAlign.Center,
                    fontSize = TextSizeUtil(textType = "ITEM_TEXT_H4"),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button( modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        setShowDialog(false)
                    }) {
                    Text(text = "Close")
                }


            }
        }
    }
}

@Composable
fun LoadingDialog(setShowDialog: (Boolean) -> Unit) {

    Dialog( properties = DialogProperties(dismissOnClickOutside = false),
        onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.SemiBold,
                    text = "LOADING",
                    textAlign = TextAlign.Center,
                    fontSize = TextSizeUtil(textType = "ITEM_TEXT_H1"),
                )
                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier.padding(20.dp)) {

                    LoadingEffect()
                }
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Light,
                    text = "Please wait while loading..",
                    textAlign = TextAlign.Center,
                    fontSize = TextSizeUtil(textType = "ITEM_TEXT_H4"),
                )
                Spacer(modifier = Modifier.height(10.dp))



            }
        }
    }
}

@Composable
fun  AddFundsDialog( viewModel: PortfolioViewModel, setShowDialog: (Boolean) -> Unit) {

    var isCurrencySelection by rememberSaveable { mutableStateOf(false) }
    var countryCode by remember { mutableStateOf("EUR") }
    var currencyCode by remember { mutableStateOf("EUR") }

    Dialog(
        properties = DialogProperties(dismissOnClickOutside = false),
        onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.SemiBold,
                    text =  "ADD FUNDS",
                    textAlign = TextAlign.Center,
                    fontSize = TextSizeUtil(textType = "ITEM_TEXT_H1"),
                )
                Spacer(modifier = Modifier.height(15.dp))

                Image( painter = painterResource(R.drawable.add_funds),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(15.dp))
                Column( modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Select a currency",
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedButton(onClick = { isCurrencySelection = true }) {
                        Image(
                            painter = rememberAsyncImagePainter(Constant.IMAGE_API+countryCode),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(25.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = currencyCode,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(if (isCurrencySelection) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            "contentDescription",
                            Modifier.clickable { isCurrencySelection = !isCurrencySelection })
                    }

                    // Create a drop-down menu with list of cities,
                    // when clicked, set the Text Field text as the city selected
                    DropdownMenu(
                        expanded = isCurrencySelection,
                        onDismissRequest = { isCurrencySelection = false },
                    ) {
                        Constant.ADD_CURRENCY_CODES.forEach { label ->
                            DropdownMenuItem(onClick = {
                                isCurrencySelection = false
                                currencyCode = label.currencyCode
                                countryCode = label.countryCode
                                viewModel.onEvent(PortfolioPageEvents.onSelectCurrencyType(label.currencyCode))
                            }) {

                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = label.currencyCode + " / " + label.countryName
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value =viewModel.amount,
                    onValueChange = { viewModel.onEvent(PortfolioPageEvents.onAmountChange(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    label = { Text("Amount")}
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row() {
                    Box(modifier = Modifier.weight(1f)) {
                        Button( modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                setShowDialog(false)

                            }) {
                            Text(text = "Cancel")
                        }

                    }
                   Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        Button( modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                setShowDialog(false)
                                viewModel.onEvent(PortfolioPageEvents.onClickAddFunds)
                            }) {
                            Text(text = "Deposit")
                        }

                    }
                }


            }
        }
    }
}
