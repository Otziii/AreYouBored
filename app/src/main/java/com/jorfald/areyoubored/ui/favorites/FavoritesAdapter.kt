package com.jorfald.areyoubored.ui.favorites

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.areyoubored.database.ToDoObject
import com.jorfald.areyoubored.ui.views.WhatToDoCardView

class FavoritesAdapter(
    private val favoriteClicked: (ToDoObject) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {
    private var dataSet: List<ToDoObject> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = WhatToDoCardView(parent.context, null)

        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val toDoObject = dataSet[position]

        holder.favoriteView.setTitle(toDoObject.activity)
        holder.favoriteView.setParticipants(toDoObject.participants)
        holder.favoriteView.setPrice(toDoObject.price)
        holder.favoriteView.setType(toDoObject.type)
        holder.favoriteView.setLink(toDoObject.link)
        holder.favoriteView.setFavorite(true)

        holder.favoriteView.setFavoritesButtonClicked {
            favoriteClicked(toDoObject)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateData(list: List<ToDoObject>) {
        dataSet = list
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(val favoriteView: WhatToDoCardView) :
        RecyclerView.ViewHolder(favoriteView)
}
