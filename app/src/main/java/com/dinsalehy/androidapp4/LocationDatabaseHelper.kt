package com.dinsalehy.androidapp4

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

// Data model
data class Location(
    val id: Long,
    val name: String,
    val imageResId: Int
)

// SQLite Helper
class LocationDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "locations.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // Create table with id and name
        val createTableSQL = """
            CREATE TABLE visited_places (
                id INTEGER PRIMARY KEY,
                name TEXT
            )
        """.trimIndent()
        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS visited_places")
        onCreate(db)
    }

    // Function to insert id and name
    fun insertVisitedPlace(id: Int, name: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id", id)
            put("name", name)
        }
        db.insert("visited_places", null, values)
    }
}


