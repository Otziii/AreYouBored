package com.jorfald.areyoubored.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorfald.areyoubored.ToDoRepository
import com.jorfald.areyoubored.database.AppDatabase
import com.jorfald.areyoubored.database.ToDoObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {
    private val repository = ToDoRepository()
    val favoritesListLiveData: MutableLiveData<List<ToDoObject>> = MutableLiveData()

    fun fetchAllFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = repository.fetchAllToDoItemsFromDB()

            favoritesListLiveData.postValue(list)
        }
    }

    fun deleteFavorite(favoriteToDelete: ToDoObject) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteToDoItemFromDB(favoriteToDelete)

            fetchAllFavorites()
        }
    }
}