package com.example.ed_currencyconverter.Data.LocalDataSource.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TransactionRecords(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val transactionDate: String,
    val soldAmount: Double,
    val soldCurrency: String,
    val purchasedCurrency: String,
    val purchasedValue: Double,
    val fromCountryCode: String,
    val toCountryCode: String,
    val commission: Double,
    )
