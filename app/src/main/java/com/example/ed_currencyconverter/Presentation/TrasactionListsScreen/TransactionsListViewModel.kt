package com.example.ed_currencyconverter.Presentation.TrasactionListsScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ed_currencyconverter.Domain.UseCases.LocalDbOperation
import com.example.ed_currencyconverter.Utils.CommonUiEvents
import com.example.ed_currencyconverter.Utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class TransactionsListViewModel @Inject constructor(val localDbOperation: LocalDbOperation)  : ViewModel() {



    private val _state = mutableStateOf(TransactionListStates())
    val state: State<TransactionListStates> = _state

    private val _eventFlow = MutableSharedFlow<CommonUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    init{

        loadConversionRecords()
    }




    private fun loadConversionRecords(){

        _state.value = TransactionListStates(isLoading = true)
        viewModelScope.launch {
            try {
                localDbOperation.getTransactions().collect{ it
                    if(it.size > 0){
                        _state.value = TransactionListStates(records = it)
                    }else {
                        _state.value = TransactionListStates(isEmpty = true)
                    }
                }
            } catch(e: IOException) {
                _state.value = TransactionListStates(isError = true)
                Log.d("INITIALIZED RECORDS","(IOException) " +e.localizedMessage)
            }  catch(e: Exception) {
                _state.value = TransactionListStates(isError = true)
                Log.d("INITIALIZED RECORDS","(Exception) " +e.localizedMessage)
            }
        }

    }


    fun onEvent(event: TransactionListPageEvents)
    {
        when(event)
        {
            is TransactionListPageEvents.onClickAppBarItem ->{
                viewModelScope.launch {
                    if(event.itemId.equals(Constant.main_page)){
                        _eventFlow.emit(CommonUiEvents.Navigate(Constant.main_page))
                    }else if(event.itemId.equals(Constant.portfolio_page)){
                        _eventFlow.emit(CommonUiEvents.Navigate(Constant.portfolio_page))
                    }else if(event.itemId.equals(Constant.exit_page)){
                        _eventFlow.emit(CommonUiEvents.CloseApp)
                    }else{
                        return@launch
                    }
                }

            }
        }
    }


}