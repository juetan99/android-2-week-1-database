package com.ucsdextandroid2.todoroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration

/**
 * Created by rjaylward on 2019-07-05
 */
@Database(entities = [Note::class], version = 1)
@TypeConverters(UriTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NotesDao
    companion object {

        private const val DB_NAME = "notesapp.db"

        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }


        private fun buildDatabase(context: Context): AppDatabase {
            return  Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .build()
        }
    }

}
