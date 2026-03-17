package mx.itson.itson.persistence

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import mx.itson.cheems.persistence.CheemsDB.CheemsDB

class Winner {
    var id = 0
    var name : String = ""
    var nickname : String = ""

    constructor()
    constructor(id: Int, name: String, nickname: String){
        this.id = id
        this.name = name
        this.nickname = nickname

    }

    fun save(context: Context, name: String, nickname: String){
        try {
            val cheemsDB = CheemsDB(context, "CheemsDB", null, 1)
            val dataBase : SQLiteDatabase = cheemsDB.writableDatabase
            val values = ContentValues()
            values.put("name", name)
            values.put("nickname", nickname)


            dataBase.insert("Winners", null, values)

            //hits -> pistas

        }catch (ex: Exception){
            Log.d("Error saving winner", ex.message.toString())
        }
    }

    fun getAll (context: Context) : List<Winner>{
        var winners : MutableList<Winner> = ArrayList()
        try {
            val cheemsDB = CheemsDB(context, "CheemsDB", null, 1)
            val dataBase : SQLiteDatabase = cheemsDB.readableDatabase
            val resultSet = dataBase.rawQuery("SELECT id, name, nickname FROM Winners",null)
            while (resultSet.moveToNext()){
                val winner = Winner(resultSet.getInt(0),resultSet.getString(1), resultSet.getString(2))
                winners.add(winner)
            }

        }catch (ex: Exception){
            Log.d("Error getting winner", ex.message.toString())
        }
        return winners
    }
}