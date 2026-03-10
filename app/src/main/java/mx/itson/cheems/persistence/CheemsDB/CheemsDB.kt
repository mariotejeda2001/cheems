package mx.itson.cheems.persistence.CheemsDB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class CheemsDB( context: Context, name: String, factoy: SQLiteDatabase.CursorFactory?,
    version: Int) : SQLiteOpenHelper(context, name, factoy, version) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
    try {
        sqLiteDatabase.execSQL("CREATE TABLE Winners (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, nickname TEXT )")
    }catch (ex: Exception){
        Log.d("Error al cargar la base de datos", ex.message.toString())
    }
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }
}