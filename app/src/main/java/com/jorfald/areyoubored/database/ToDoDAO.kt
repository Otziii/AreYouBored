package com.jorfald.areyoubored.database

import androidx.room.*

@Dao
interface ToDoDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItem(toDoObject: ToDoObject)

    @Delete
    fun deleteItem(toDoObject: ToDoObject)

    @Query("SELECT * FROM to_do")
    fun getAllItems(): List<ToDoObject>
}