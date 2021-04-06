package com.jorfald.areyoubored.ui.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.jorfald.areyoubored.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private lateinit var peopleSpinner: Spinner
    private lateinit var moneyButton1: AppCompatButton
    private lateinit var moneyButton2: AppCompatButton
    private lateinit var moneyButton3: AppCompatButton

    private lateinit var startButton: AppCompatButton

    var selectedMoney = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome)

        peopleSpinner = welcome_people_spinner
        moneyButton1 = welcome_money_button_1
        moneyButton2 = welcome_money_button_2
        moneyButton3 = welcome_money_button_3
        startButton = welcome_start_button

        setUpSpinner()
        bindButtons()
    }

    private fun setUpSpinner() {

    }

    private fun bindButtons() {
        moneyButton1.setOnClickListener {
            setCorrectMoneyButton(1)
        }

        moneyButton2.setOnClickListener {
            setCorrectMoneyButton(2)
        }

        moneyButton3.setOnClickListener {
            setCorrectMoneyButton(3)
        }
    }

    private fun setCorrectMoneyButton(selectedButton: Int) {
        selectedMoney = selectedButton

        when (selectedButton) {
            2 -> {
                moneyButton1.background = ContextCompat.getDrawable(this, R.drawable.unselected_button)
                moneyButton2.background = ContextCompat.getDrawable(this, R.drawable.selected_button)
                moneyButton3.background = ContextCompat.getDrawable(this, R.drawable.unselected_button)
                moneyButton1.setTextColor(ContextCompat.getColor(this, R.color.blue))
                moneyButton2.setTextColor(ContextCompat.getColor(this, R.color.white))
                moneyButton3.setTextColor(ContextCompat.getColor(this, R.color.blue))
            }
            3 -> {
                moneyButton1.background = ContextCompat.getDrawable(this, R.drawable.unselected_button)
                moneyButton2.background = ContextCompat.getDrawable(this, R.drawable.unselected_button)
                moneyButton3.background = ContextCompat.getDrawable(this, R.drawable.selected_button)
                moneyButton1.setTextColor(ContextCompat.getColor(this, R.color.blue))
                moneyButton2.setTextColor(ContextCompat.getColor(this, R.color.blue))
                moneyButton3.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
            else -> {
                moneyButton1.background = ContextCompat.getDrawable(this, R.drawable.selected_button)
                moneyButton2.background = ContextCompat.getDrawable(this, R.drawable.unselected_button)
                moneyButton3.background = ContextCompat.getDrawable(this, R.drawable.unselected_button)
                moneyButton1.setTextColor(ContextCompat.getColor(this, R.color.white))
                moneyButton2.setTextColor(ContextCompat.getColor(this, R.color.blue))
                moneyButton3.setTextColor(ContextCompat.getColor(this, R.color.blue))
            }
        }
    }
}