package com.example.ed_currencyconverter.Domain.Repositories

import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.CurrencyAccount
import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.TransactionRecords
import com.example.ed_currencyconverter.Data.LocalDataSource.RoomDatabaseDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionsRepository @Inject constructor(val dao: RoomDatabaseDao) {

    suspend fun getCurrencyAccount(code: String): CurrencyAccount?{
        return dao.getCurrencyAccount(code)
    }

    fun getCurrencyAccounts(): Flow<List<CurrencyAccount>>{
        return dao.getCurrencies()
    }


    fun  updateCurrencyBalance(code: String ,newBalance: Double, dateUpdated: String) {
        return  dao.updateBalance(code,newBalance,dateUpdated)
    }

    fun addCurrencyBalance(code: String ,newBalance: Double) {
        return  dao.addBalance(code,newBalance)
    }

    fun getTransactionRecords(): Flow<List<TransactionRecords>> {
        return dao.getTransactions();
    }

    suspend fun  insertTransactionRecord(transactionRecord: TransactionRecords) {

        return  dao.insertTransaction(transactionRecord)
    }



}