package com.jorfald.areyoubored.ui.random

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.jorfald.areyoubored.R
import com.jorfald.areyoubored.SHARED_PREF_FILE_NAME
import com.jorfald.areyoubored.SHARED_PREF_KEY_MONEY
import com.jorfald.areyoubored.SHARED_PREF_KEY_PEOPLE
import com.jorfald.areyoubored.ui.views.WhatToDoCardView
import kotlinx.android.synthetic.main.fragment_random.view.*

class RandomFragment : Fragment() {

    private lateinit var randomViewModel: RandomViewModel

    private lateinit var randomCard: WhatToDoCardView
    private lateinit var newButton: AppCompatButton
    private lateinit var loader: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        randomViewModel = ViewModelProvider(this).get(RandomViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_random, container, false)

        randomCard = view.random_card
        newButton = view.random_new_button
        loader = view.random_lottie_loader

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindButtons()
        bindObservers()
        fetchRandomActivity()
    }

    private fun bindButtons() {
        newButton.setOnClickListener {
            fetchRandomActivity()
        }
    }

    private fun bindObservers() {
        randomViewModel.showLoader.observe(viewLifecycleOwner) { isLoading ->
            loader.isVisible = isLoading
        }

        randomViewModel.showErrorDialog.observe(viewLifecycleOwner) { shouldShow ->
            if (shouldShow) {
                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_LONG).show()
            }
        }

        randomViewModel.fetchedToDoObject.observe(viewLifecycleOwner) { toDoObject ->
            randomCard.isVisible = true

            randomCard.setTitle(toDoObject.activity)
            randomCard.setParticipants(toDoObject.participants)
            randomCard.setPrice(toDoObject.price)
            randomCard.setType(toDoObject.type)
            randomCard.setLink(toDoObject.link)
            randomCard.setFavorite(false)

            randomCard.setFavoritesButtonClicked {
                randomViewModel.saveFavorite(toDoObject)

                randomCard.setFavorite(true)
                fetchRandomActivity()
            }
        }
    }

    private fun fetchRandomActivity() {
        val sharedPrefs =
            requireContext().getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val people = sharedPrefs.getInt(SHARED_PREF_KEY_PEOPLE, 1)
        val money = sharedPrefs.getInt(SHARED_PREF_KEY_MONEY, 1)

        randomViewModel.fetchRandomActivity(people, money)
    }
}