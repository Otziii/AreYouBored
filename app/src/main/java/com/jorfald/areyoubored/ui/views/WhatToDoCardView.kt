package com.jorfald.areyoubored.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.jorfald.areyoubored.R
import kotlinx.android.synthetic.main.what_to_do_card.view.*

class WhatToDoCardView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private val titleTextView: TextView
    private val participantsWrapper: LinearLayout
    private val priceColoredTextView: TextView
    private val priceNotColoredTextView: TextView
    private val typeTextView: TextView
    private val linkTextButton: TextView
    private val favoritesButton: AppCompatImageButton

    init {
        val view: View = LayoutInflater.from(context).inflate(R.layout.what_to_do_card, this)

        titleTextView = view.do_title
        participantsWrapper = view.do_participants_wrapper
        priceColoredTextView = view.do_price_colored
        priceNotColoredTextView = view.do_price_not_colored
        typeTextView = view.do_type_text
        linkTextButton = view.do_link_button
        favoritesButton = view.do_favorites_button
    }

    fun setTitle(title: String) {
        titleTextView.text = title
    }

    fun setParticipants(numberOfParticipants: Int) {
        participantsWrapper.removeAllViews()

        for (i in 0 until numberOfParticipants) {
            val imageView = ImageView(context)
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_person))
            participantsWrapper.addView(imageView)
        }
    }

    fun setPrice(priceAmount: Double) {


        when {
            priceAmount == 0.0 -> {
                priceColoredTextView.text = ""
                priceNotColoredTextView.text = "$$$$$"
            }
            priceAmount <= 0.2 -> {
                priceColoredTextView.text = "$"
                priceNotColoredTextView.text = "$$$$"
            }
            priceAmount <= 0.4 -> {
                priceColoredTextView.text = "$$"
                priceNotColoredTextView.text = "$$$"
            }
            priceAmount <= 0.6 -> {
                priceColoredTextView.text = "$$$"
                priceNotColoredTextView.text = "$$"
            }
            priceAmount <= 0.8 -> {
                priceColoredTextView.text = "$$$$"
                priceNotColoredTextView.text = "$"
            }
            else -> {
                priceColoredTextView.text = "$$$$$"
                priceNotColoredTextView.text = ""
            }
        }
    }

    fun setType(type: String) {
        typeTextView.text = type
    }

    fun setFavoritesButtonClicked(clickListener: OnClickListener) {
        favoritesButton.setOnClickListener(clickListener)
    }

    fun setLink(link: String?) {
        if (!link.isNullOrEmpty()) {
            linkTextButton.isVisible = true

            linkTextButton.setOnClickListener {
                Toast.makeText(context, "Link clicked", Toast.LENGTH_SHORT).show()
                //TODO: Open link in browser
            }
        } else {
            linkTextButton.isVisible = false
        }
    }
}