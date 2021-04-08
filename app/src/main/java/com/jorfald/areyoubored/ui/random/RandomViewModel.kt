package com.jorfald.areyoubored.ui.random

import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.jorfald.areyoubored.ui.ToDoObject

class RandomViewModel : ViewModel() {
    fun fetchRandomActivity(
        requestQueue: RequestQueue,
        people: Int,
        money: Int,
        callback: (ToDoObject?) -> Unit
    ) {
        val price = convertMoneyToPrice(money)
        var url = "https://www.boredapi.com/api/activity/"
        url += "?participants=$people&maxprice=$price"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { jsonResponse ->
                val convertedObject = Gson().fromJson(jsonResponse, ToDoObject::class.java)
                callback(convertedObject)
            },
            { error ->
                callback(null)
            }
        )

        requestQueue.add(stringRequest)
    }

    private fun convertMoneyToPrice(money: Int): Double {
        return when (money) {
            1 -> {
                0.0
            }
            2 -> {
                0.5
            }
            else -> {
                1.0
            }
        }
    }
}