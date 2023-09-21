package com.ivanojok.myshop.data.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class DBBuilder {
    fun initializeDB(context: Context): AppDatabase {
        val builder = Room.databaseBuilder(context, AppDatabase::class.java, "cartDB").fallbackToDestructiveMigration()
        return builder.build()
    }
}