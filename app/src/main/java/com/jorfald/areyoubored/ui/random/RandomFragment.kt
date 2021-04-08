package com.jorfald.areyoubored.ui.random

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.Volley
import com.jorfald.areyoubored.R
import com.jorfald.areyoubored.ui.SHARED_PREF_FILE_NAME
import com.jorfald.areyoubored.ui.SHARED_PREF_KEY_MONEY
import com.jorfald.areyoubored.ui.SHARED_PREF_KEY_PEOPLE
import com.jorfald.areyoubored.ui.views.WhatToDoCardView
import kotlinx.android.synthetic.main.fragment_random.view.*

class RandomFragment : Fragment() {

    private lateinit var randomViewModel: RandomViewModel

    private lateinit var randomCard: WhatToDoCardView
    private lateinit var newButton: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        randomViewModel = ViewModelProvider(this).get(RandomViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_random, container, false)

        randomCard = view.random_card
        newButton = view.random_new_button

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindButtons()
        fetchRandomActivity()
    }

    private fun bindButtons() {
        newButton.setOnClickListener {
            fetchRandomActivity()
        }
    }

    private fun fetchRandomActivity() {
        val sharedPrefs = requireContext().getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
        val people = sharedPrefs.getInt(SHARED_PREF_KEY_PEOPLE, 1)
        val money = sharedPrefs.getInt(SHARED_PREF_KEY_MONEY, 1)

        val requestQueue = Volley.newRequestQueue(context)
        randomViewModel.fetchRandomActivity(requestQueue, people, money) { toDoObject ->
            if (toDoObject != null) {
                randomCard.setTitle(toDoObject.activity)
                randomCard.setParticipants(toDoObject.participants)
                randomCard.setPrice(toDoObject.price)
                randomCard.setType(toDoObject.type)
                randomCard.setLink(toDoObject.link)

                randomCard.setFavoritesButtonClicked {
                    Toast.makeText(context, "Favorite clicked", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_LONG).show()
            }
        }
    }
}