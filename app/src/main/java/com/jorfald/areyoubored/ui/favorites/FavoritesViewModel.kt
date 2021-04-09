package com.jorfald.areyoubored.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorfald.areyoubored.database.AppDatabase
import com.jorfald.areyoubored.database.ToDoObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {
    val favoritesListLiveData: MutableLiveData<List<ToDoObject>> = MutableLiveData()

    fun fetchAllFavorites(database: AppDatabase) {
        CoroutineScope(Dispatchers.IO).launch {
            val toDoDAO = database.toDoDAO()
            val list = toDoDAO.getAllItems()

            favoritesListLiveData.postValue(list)
        }
    }

    fun deleteFavorite(database: AppDatabase, favoriteToDelete: ToDoObject) {
        CoroutineScope(Dispatchers.IO).launch {
            val toDoDAO = database.toDoDAO()
            toDoDAO.deleteItem(favoriteToDelete)

            fetchAllFavorites(database)
        }
    }
}