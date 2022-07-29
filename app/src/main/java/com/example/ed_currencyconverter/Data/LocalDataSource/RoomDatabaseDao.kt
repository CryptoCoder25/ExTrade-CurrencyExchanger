package com.example.ed_currencyconverter.Data.LocalDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.CurrencyAccount
import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.TransactionRecords
import kotlinx.coroutines.flow.Flow


@Dao
interface RoomDatabaseDao {


    @Query("SELECT * FROM CurrencyAccount")
     fun getCurrencies(): Flow<List<CurrencyAccount>>

    @Query("SELECT * FROM CurrencyAccount where code =:currencyCode")
    suspend fun getCurrencyAccount(currencyCode: String): CurrencyAccount?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currencyAccount: CurrencyAccount)

    @Query("UPDATE CurrencyAccount SET balance = :newBalance , dateUpdated = :dateUpdated   WHERE code = :code")
    fun updateBalance(code: String? ,newBalance: Double, dateUpdated: String?)

    @Query("UPDATE CurrencyAccount SET balance = :newBalance   WHERE code = :code")
    fun addBalance(code: String? ,newBalance: Double)

    @Query("SELECT * FROM TransactionRecords order by Id")
    fun getTransactions(): Flow<List<TransactionRecords>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transactionRecords: TransactionRecords)
}


