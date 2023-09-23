package com.ivanojok.myshop.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database([CartModel::class], version = 2)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {
     abstract fun createCartDao(): CartDao
}