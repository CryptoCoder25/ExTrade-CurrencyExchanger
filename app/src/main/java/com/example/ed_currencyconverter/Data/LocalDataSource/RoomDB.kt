package com.example.ed_currencyconverter.Data.LocalDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.CurrencyAccount
import com.example.ed_currencyconverter.Data.LocalDataSource.Entity.TransactionRecords

@Database(
    entities = [CurrencyAccount::class, TransactionRecords::class], version = 1, exportSchema = true)
abstract class RoomDB: RoomDatabase() {

    abstract val roomDatabaseDao: RoomDatabaseDao

    companion object {
        const val DATABASE_NAME = "LocalDB"
    }
}