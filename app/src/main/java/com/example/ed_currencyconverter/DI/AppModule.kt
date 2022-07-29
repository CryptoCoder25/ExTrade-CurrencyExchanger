package com.example.ed_currencyconverter.DI

import android.app.Application
import androidx.room.Room
import com.example.ed_currencyconverter.Data.LocalDataSource.RoomDB
import com.example.ed_currencyconverter.Data.RemoteDataSource.WebService
import com.example.ed_currencyconverter.Utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): WebService {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }


    @Provides
    @Singleton
    fun provideLocalDb(app: Application): RoomDB {
        return Room.databaseBuilder(
            app,
            RoomDB::class.java,
            RoomDB.DATABASE_NAME
        ).createFromAsset("database/currencyAccount.db").build()
    }


    @Provides
    @Singleton
    fun provideRoomDatabaseDao(db: RoomDB) =  db.roomDatabaseDao



}