package com.jorfald.areyoubored

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.jorfald.areyoubored.database.AppDatabase
import com.jorfald.areyoubored.database.ToDoObject

class ToDoRepository {
    /**
     * API
     */

    private val baseUrl = "https://www.boredapi.com/api/activity/"

    fun fetchRandomToDo(people: Int, price: Double, callback: (ToDoObject?) -> Unit) {
        val requestQueue =
            Volley.newRequestQueue(AreYouBoredApplication.application.applicationContext)
        val queryUrl = "$baseUrl?participants=$people&maxprice=$price"

        val stringRequest = StringRequest(
            Request.Method.GET,
            queryUrl,
            { jsonResponse ->
                val convertedObject = Gson().fromJson(jsonResponse, ToDoObject::class.java)
                callback(convertedObject)
            },
            { error ->
                Log.e("Volley_error", error.message.toString())

                callback(null)
            }
        )

        requestQueue.add(stringRequest)
    }


    /**
     * Database
     */

    private val database = AppDatabase.getDatabase(AreYouBoredApplication.application.applicationContext)
    private val toDoDao = database.toDoDAO()

    fun fetchAllToDoItemsFromDB(): List<ToDoObject> {
        return toDoDao.getAllItems()
    }

    fun addToDoItemToDB(toDoItemToAdd: ToDoObject) {
        toDoDao.addItem(toDoItemToAdd)
    }

    fun deleteToDoItemFromDB(toDoItemToDelete: ToDoObject) {
        toDoDao.deleteItem(toDoItemToDelete)
    }
}