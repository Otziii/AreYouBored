package com.jorfald.areyoubored.ui.random

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorfald.areyoubored.ToDoRepository
import com.jorfald.areyoubored.database.AppDatabase
import com.jorfald.areyoubored.database.ToDoObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomViewModel : ViewModel() {
    private val repository = ToDoRepository()

    val showLoader = MutableLiveData<Boolean>()
    val showErrorDialog = MutableLiveData<Boolean>()
    val fetchedToDoObject = MutableLiveData<ToDoObject>()

    fun fetchRandomActivity(people: Int, money: Int) {
        showLoader.value = true

        val price = convertMoneyToPrice(money)
        repository.fetchRandomToDo(people, price) { fetchedObject ->
            if (fetchedObject == null) {
                showErrorDialog.postValue(true)
            } else {
                fetchedToDoObject.postValue(fetchedObject)
            }

            showLoader.postValue(false)
        }
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

    fun saveFavorite(favorite: ToDoObject) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToDoItemToDB(favorite)
        }
    }
}