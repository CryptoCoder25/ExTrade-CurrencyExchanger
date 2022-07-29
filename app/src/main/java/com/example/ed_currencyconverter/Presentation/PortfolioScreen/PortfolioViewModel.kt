package com.example.ed_currencyconverter.Presentation.PortfolioScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ed_currencyconverter.Domain.UseCases.LocalDbOperation
import com.example.ed_currencyconverter.Presentation.MainScreen.MainPageEvents
import com.example.ed_currencyconverter.Utils.CommonUiEvents
import com.example.ed_currencyconverter.Utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*
import javax.inject.Inject


@HiltViewModel
class PortfolioViewModel  @Inject constructor(val localDbOperation: LocalDbOperation)  : ViewModel() {

    var amount by mutableStateOf("")
        private set
    var currencyCode by mutableStateOf("EUR")
        private set
    var diplayBalance by mutableStateOf("0.00")
        private set

    private val _state = mutableStateOf(PortfolioPageStates())
    val state: State<PortfolioPageStates> = _state

    private val _eventFlow = MutableSharedFlow<CommonUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    init{

        loadCurrencies()
    }




    private fun loadCurrencies(){

        _state.value = PortfolioPageStates(isLoading = true)
        viewModelScope.launch {
            try {
                diplayBalance =  "€"+(localDbOperation.getAccountStats(currencyCode)?.balance ?: 0.00).toString()
                localDbOperation.getAccountCurrencies().collect{ it
                    if(it.size > 0){
                        _state.value = PortfolioPageStates(records = it)
                    }else {
                        _state.value =  PortfolioPageStates(isEmpty = true)
                    }
                }
            } catch(e: IOException) {
                _state.value =  PortfolioPageStates(isError = true)
                Log.d(Constant.INIT_PORTFOLIO_VM,"(IOException) " +e.localizedMessage)

            }  catch(e: Exception) {
                _state.value =  PortfolioPageStates(isError = true)
                Log.d(Constant.INIT_PORTFOLIO_VM,"(Exception) " +e.localizedMessage)
            }
        }

    }


    fun onEvent(event: PortfolioPageEvents)
    {
        when(event)
        {
            is PortfolioPageEvents.onClickAppBarItem ->{
                viewModelScope.launch {
                    if(event.itemId.equals(Constant.main_page)){
                        _eventFlow.emit(CommonUiEvents.Navigate(Constant.main_page))
                    }else if(event.itemId.equals(Constant.TransactionListScreen)){
                        _eventFlow.emit(CommonUiEvents.Navigate(Constant.sub_page))
                    }else if(event.itemId.equals(Constant.exit_page)){
                        _eventFlow.emit(CommonUiEvents.CloseApp)
                    }else{
                        return@launch
                    }
                }

            }
            is PortfolioPageEvents.onClickOpenAddFundDialog ->{

               viewModelScope.launch {
                   _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.ADD_FUNDS,""))

               }

            }
            is PortfolioPageEvents.onAmountChange ->{

                    amount = event.amount

              }

            is PortfolioPageEvents.onSelectCurrencyType ->{

                currencyCode = event.currencyCode

            }

            is PortfolioPageEvents.onClickAddFunds ->{

                viewModelScope.launch(Dispatchers.IO) {

                    if ((amount.replace(("[^\\w\\d ]").toRegex(), "")).isBlank()) {
                        _eventFlow.emit(CommonUiEvents.ShowToastMessage("Amount is required!"))
                        amount = ""
                        return@launch
                    }

                    if ( amount.toDouble() < 1.0) {
                        amount = ""
                        _eventFlow.emit(CommonUiEvents.ShowToastMessage("Amount is too low!"))
                        return@launch
                    }
                    if (currencyCode.isBlank()) {
                        _eventFlow.emit(CommonUiEvents.ShowToastMessage("Currency type is required!"))
                        return@launch
                    }

                    processTransaction();
                }
            }
        }
    }

    private suspend fun processTransaction(){

        try {

            localDbOperation.addCurencyBalance(currencyCode,amount.toDouble())

            _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.SUCCESS_ADD_FUNDS,Constant.SUCCESS_ADD_FUNDS_BODY))
             amount =  ""
            diplayBalance =  "€"+(localDbOperation.getAccountStats("EUR")?.balance ?: 0.00).toString()
        } catch(e: IOException) {

            _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.INTERNAL,Constant.INTERNAL_MESSAGEBODY))

            Log.d(Constant.CRUD_PORTFOLIO_VM,"(IOException) " +e.localizedMessage)

        }  catch(e: Exception) {
            _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.INTERNAL,Constant.INTERNAL_MESSAGEBODY))
            Log.d(Constant.CRUD_PORTFOLIO_VM,"(Exception) " +e.localizedMessage)
        }
    }



}