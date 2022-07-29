package com.example.ed_currencyconverter.Domain.UseCases

import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.CurrencyAccount
import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.TransactionRecords
import com.example.ed_currencyconverter.Domain.Repositories.TransactionsRepository
import kotlinx.coroutines.flow.Flow
import java.util.HashMap
import javax.inject.Inject

class LocalDbOperation  @Inject constructor (private val transactionRepository: TransactionsRepository){

    fun getAccountCurrencies(): Flow<List<CurrencyAccount>> {
        return transactionRepository.getCurrencyAccounts()
    }

    fun getTransactions(): Flow<List<TransactionRecords>> {
        return transactionRepository.getTransactionRecords()
    }

  suspend fun saveTransaction(
        conversionData : HashMap<String, String>,
        soldValue: Double,
        transactionDigit: Pair<Double, Double>){


      transactionRepository.insertTransactionRecord(
            TransactionRecords(
                transactionDate = conversionData.get("DATE").toString(),
                soldAmount = soldValue,
                soldCurrency = conversionData.get("SOLD_CURRENCY").toString(),
                purchasedCurrency = conversionData.get("BOUGHT_CURRENCY").toString(),
                purchasedValue = transactionDigit.first,
               fromCountryCode =  conversionData.get("FROM_COUNTRY_CODE").toString(),
               toCountryCode=  conversionData.get("TO_COUNTRY_CODE").toString(),
               commission = transactionDigit.second,
            )
        )

    }


   suspend fun updateBalance(
       transactionType: String, code: String,
       soldValue: Double, transactionDigit :
       Pair<Double, Double>,
       transactionDate: String){


         var newBalance : Double;
          var previousBalance = getAccountStats(code)?.balance ?: 0.00


         if(transactionType.equals("SOLD"))
        {
             val totalFees = ("%.2f".format(soldValue + (soldValue * transactionDigit.second))).toDouble()
            newBalance =  ("%.2f".format( previousBalance - totalFees )).toDouble()


        }else{

            newBalance = ("%.2f".format( previousBalance + transactionDigit.first)).toDouble();
        }

       transactionRepository.updateCurrencyBalance(code,newBalance,transactionDate)

    }


    suspend fun getAccountStats(code: String): CurrencyAccount? {

        return transactionRepository.getCurrencyAccount(code)
    }

    suspend fun addCurencyBalance(code: String, amount: Double) {

        var previousBalance = getAccountStats(code)?.balance ?: 0.00
       val newBalance = ("%.2f".format( previousBalance + amount)).toDouble();
       transactionRepository.addCurrencyBalance(code,newBalance)

    }

}