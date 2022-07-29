package com.example.ed_currencyconverter.Presentation.MainScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ed_currencyconverter.Domain.Repositories.LatestRateRepository
import com.example.ed_currencyconverter.Domain.UseCases.LocalDbOperation
import com.example.ed_currencyconverter.Utils.ApiResponseStates
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
import kotlin.math.round


@HiltViewModel
class MainPageViewModel  @Inject constructor(val repository: LatestRateRepository,
                                             val localDbOperation: LocalDbOperation)  : ViewModel()  {

    var sellingCountryCode by mutableStateOf("EUR")
        private set
    var buyingCountryCode by mutableStateOf("US")
        private set
    var amount by mutableStateOf("")
        private set
    var sellingCurrency by mutableStateOf("EUR")
        private set
    var buyingCurrency by mutableStateOf("USD")
        private set
    var countRecords by mutableStateOf(0)
        private set
    var accountBalance  by mutableStateOf(0.00)
        private set
    var targetCurrencyBalance by mutableStateOf(0.00)
        private set

    private val _eventFlow = MutableSharedFlow<CommonUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    var conversionData : HashMap<String,String> = HashMap<String, String> ()
        private set



    init {

        viewModelScope.launch(Dispatchers.IO) {

                localDbOperation.getTransactions().collect{
                    countRecords =  it.size
                    accountBalance = localDbOperation.getAccountStats(sellingCurrency)?.balance ?: 0.00
                    targetCurrencyBalance =  (localDbOperation.getAccountStats(buyingCurrency)?.balance ?: 0.00)

                }

        }
    }



  fun onEvent(event: MainPageEvents)
    {
        when(event)
        {
            is MainPageEvents.onClickAppBarItem ->{
               viewModelScope.launch {
                   if(event.itemId.equals(Constant.portfolio_page)){
                       _eventFlow.emit(CommonUiEvents.Navigate(Constant.portfolio_page))
                   } else if(event.itemId.equals(Constant.sub_page)){
                        _eventFlow.emit(CommonUiEvents.Navigate(Constant.sub_page))
                   }else if(event.itemId.equals(Constant.exit_page)){
                        _eventFlow.emit(CommonUiEvents.CloseApp)
                   }else{
                       return@launch
                   }
               }

            }
            is MainPageEvents.onAmountChange ->{
                amount = event.amount
            }

            is MainPageEvents.onSelectSell ->{
                amount =  ""
                sellingCurrency = event.sellingCurrency
                sellingCountryCode = event.sellingCountryCode

                viewModelScope.launch(Dispatchers.IO) {
                    accountBalance = localDbOperation.getAccountStats(sellingCurrency)?.balance ?: 0.00
                }

            }
            is MainPageEvents.onSelectBuy -> {
               buyingCurrency = event.buyingCurrency
                buyingCountryCode = event.buyingCountryCode

                viewModelScope.launch(Dispatchers.IO) {
                        targetCurrencyBalance =  (localDbOperation.getAccountStats(buyingCurrency)?.balance ?: 0.00)
                }

            }
            is MainPageEvents.onClickConvertBtn ->{

                viewModelScope.launch(Dispatchers.IO) {

                    if ((amount.replace(("[^\\w\\d ]").toRegex(), "")).isBlank()) {
                        _eventFlow.emit(CommonUiEvents.ShowToastMessage("Amount is required!"))
                        amount = ""
                        return@launch
                    }
                    if (sellingCurrency.isBlank()) {
                        _eventFlow.emit(CommonUiEvents.ShowToastMessage("Selling currrency is required!"))
                        return@launch
                    }
                    if (buyingCurrency.isBlank()) {
                        _eventFlow.emit(CommonUiEvents.ShowToastMessage("Buying currency is required!"))
                        return@launch
                    }

                    if (sellingCurrency.equals(buyingCurrency)) {
                     _eventFlow.emit(CommonUiEvents.ShowToastMessage("Unable to convert same currency!"))
                      return@launch
                    }

                    if(amount.toDouble() < 1.0){
                        _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.INTERNAL,"1$sellingCurrency is the minimum transaction is  for $sellingCurrency account."))
                        return@launch
                    }

                    accountBalance = localDbOperation.getAccountStats(sellingCurrency)?.balance ?: 0.00

                    if((accountBalance < amount.toDouble())){

                            _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.INTERNAL,"You don't have enough balance in $sellingCurrency account."))
                            return@launch
                    }

                    if( (("%.2f".format(accountBalance)).toDouble() == ("%.2f".format(amount.toDouble())).toDouble()) &&  !(countRecords < 5 || (countRecords + 1 ) % 10 == 0)){

                        _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.INTERNAL,"You don't have enough balance in $sellingCurrency account."))
                        return@launch
                    }



                    _eventFlow.emit(CommonUiEvents.LoadingProcess)
                    processTransaction();
                }
           }

        }


    }


  private suspend fun processTransaction(){

          val result = repository.getLatestRates(sellingCurrency)

          when(result) {

              is ApiResponseStates.Error ->{

                  Log.d(Constant.API_MAINPAGE_VM,"(IOException) "+ result.message)
                 _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.EXTERNAL,Constant.EXTERNAL_MESSAGEBODY))

              }
              is ApiResponseStates.Success -> {

                      val convertedValue = result.data?.rates?.let {
                          ("%.2f".format(amount.toDouble().times(it.get(buyingCurrency)?.toDoubleOrNull()!!))).toDouble()
                      }

                   if(!convertedValue.toString().isNullOrEmpty()){

                       conversionData.put("FROM_COUNTRY_CODE",sellingCountryCode)
                       conversionData.put("TO_COUNTRY_CODE",buyingCountryCode)
                       conversionData.put("SOLD_CURRENCY",sellingCurrency)
                       conversionData.put("BOUGHT_CURRENCY", buyingCurrency)
                       conversionData.put("DATE",result.data!!.date)

                       try {
                           localDbOperation.saveTransaction(
                               conversionData,
                               amount.toDouble(),
                               getCommissionFee(convertedValue!!))

                           localDbOperation.updateBalance("SOLD",
                               sellingCurrency,
                               amount.toDouble(),
                               getCommissionFee(convertedValue!!),
                               result.data!!.date)

                           localDbOperation.updateBalance("BOUGHT",
                               buyingCurrency,
                               amount.toDouble(),
                               getCommissionFee(convertedValue!!),
                               result.data!!.date)

                          var cost = getCommissionFee(convertedValue!!)
                           var fee = ("%.2f".format(amount.toDouble() * cost.second)).toDouble()

                           _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.SUCCESS,
                               "You have converted $amount$sellingCurrency to " +
                                       cost.first.toString()+"$buyingCurrency. " +
                                       "Commission Fee: "+ (fee)+sellingCurrency))
                            amount =  ""

                           accountBalance = localDbOperation.getAccountStats(sellingCurrency)?.balance ?: 0.00
                           targetCurrencyBalance =  (localDbOperation.getAccountStats(buyingCurrency)?.balance ?: 0.00)

                       } catch(e: IOException) {
                           _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.INTERNAL,Constant.INTERNAL_MESSAGEBODY))
                           Log.d(Constant.CRUD_MAINPAGE_VM,"(IOException) " +e.localizedMessage)

                       }  catch(e: Exception) {
                           _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.INTERNAL,Constant.INTERNAL_MESSAGEBODY))
                           Log.d(Constant.CRUD_MAINPAGE_VM,"(Exception) " +e.localizedMessage)
                       }
                   }else{
                       _eventFlow.emit(CommonUiEvents.ShowDialog(Constant.INTERNAL,Constant.INTERNAL_MESSAGEBODY))
                       Log.d(Constant.CRUD_MAINPAGE_VM, convertedValue.toString())
                   }

              }
          }

  }


    private fun getCommissionFee(convertedValue: Double): Pair<Double,Double>{

        var transactionFee: Double
        if(countRecords < 5 || (countRecords + 1 ) % 10 == 0)
        {
            transactionFee = Constant.NO_COMMISSION_FEE;
        }else {
            transactionFee = Constant.COMMISSION_FEE
        }
        return Pair(convertedValue,transactionFee);

    }





}