package com.andreescajedarios.apsi.history

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andreescajedarios.apsi.history.SearchString
import com.andreescajedarios.apsi.history.SearchStringDao

@Database(entities = [SearchString::class], version = 1, exportSchema = false)
abstract class SearchStringDatabase : RoomDatabase() {

    abstract fun searchStringDao() : SearchStringDao

    companion object {

        @Volatile
        private var instance: SearchStringDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(context, SearchStringDatabase::class.java, "search_string_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .also { instance = it }
        }
    }
}