package com.example.ed_currencyconverter.Data.LocalDataSource.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyAccount(
    @PrimaryKey(autoGenerate = true)
    val  id: Int? = null,
    val  code: String?,
    val  currencyName: String?,
    val  balance: Double?,
    val  dateUpdated: String?,
    val  countryCode: String?,
    val  symbol: String? )