package com.jorfald.areyoubored.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.areyoubored.R
import com.jorfald.areyoubored.database.AppDatabase
import kotlinx.android.synthetic.main.fragment_favorites.view.*

class FavoritesFragment : Fragment() {

    private lateinit var favoritesViewModel: FavoritesViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var favoritesAdapter: FavoritesAdapter
    private lateinit var favoritesLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recyclerView = view.favorites_recycler_view

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObservers()
        initRecyclerView()
    }

    private fun bindObservers() {
        favoritesViewModel.favoritesListLiveData.observe(viewLifecycleOwner, { list ->
            favoritesAdapter.updateData(list)
        })
    }

    private fun initRecyclerView() {
        val database = AppDatabase.getDatabase(requireContext())

        favoritesAdapter = FavoritesAdapter { favoriteClicked ->
            favoritesViewModel.deleteFavorite(database, favoriteClicked)
        }

        recyclerView.adapter = favoritesAdapter

        favoritesLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = favoritesLayoutManager

        favoritesViewModel.fetchAllFavorites(database)
    }
}