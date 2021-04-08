package com.jorfald.areyoubored.ui.welcome

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.jorfald.areyoubored.MainActivity
import com.jorfald.areyoubored.R
import com.jorfald.areyoubored.ui.SHARED_PREF_FILE_NAME
import com.jorfald.areyoubored.ui.SHARED_PREF_KEY_MONEY
import com.jorfald.areyoubored.ui.SHARED_PREF_KEY_PEOPLE
import com.jorfald.areyoubored.ui.helpers.ButtonHelper.Companion.getCorrectBackground
import com.jorfald.areyoubored.ui.helpers.ButtonHelper.Companion.getCorrectTextColor
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private lateinit var peopleSpinner: Spinner
    private lateinit var moneyButton1: AppCompatButton
    private lateinit var moneyButton2: AppCompatButton
    private lateinit var moneyButton3: AppCompatButton

    private lateinit var startButton: AppCompatButton

    private var selectedPeopleNumber = 1
    private var selectedMoney = 1

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome)

        peopleSpinner = welcome_people_spinner
        moneyButton1 = welcome_money_button_1
        moneyButton2 = welcome_money_button_2
        moneyButton3 = welcome_money_button_3
        startButton = welcome_start_button

        sharedPreferences = getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)

        setUpSpinner()
        bindButtons()
        setCorrectMoneyButton(1)
    }

    private fun setUpSpinner() {
        val peopleCountList = listOf(1, 2, 3, 4, 5)

        val spinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, peopleCountList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        peopleSpinner.adapter = spinnerAdapter

        peopleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                selectedPeopleNumber = peopleCountList[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        peopleSpinner.setSelection(0)
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

        startButton.setOnClickListener {
            saveToSharedPrefs()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveToSharedPrefs() {
        val editor = sharedPreferences.edit()
        editor.putInt(SHARED_PREF_KEY_PEOPLE, selectedPeopleNumber)
        editor.putInt(SHARED_PREF_KEY_MONEY, selectedMoney)
        editor.apply()
    }

    private fun setCorrectMoneyButton(selectedButton: Int) {
        selectedMoney = selectedButton

        when (selectedButton) {
            2 -> {
                moneyButton1.background = getCorrectBackground(this, false)
                moneyButton2.background = getCorrectBackground(this, true)
                moneyButton3.background = getCorrectBackground(this, false)
                moneyButton1.setTextColor(getCorrectTextColor(this, false))
                moneyButton2.setTextColor(getCorrectTextColor(this, true))
                moneyButton3.setTextColor(getCorrectTextColor(this, false))
            }
            3 -> {
                moneyButton1.background = getCorrectBackground(this, false)
                moneyButton2.background = getCorrectBackground(this, false)
                moneyButton3.background = getCorrectBackground(this, true)
                moneyButton1.setTextColor(getCorrectTextColor(this, false))
                moneyButton2.setTextColor(getCorrectTextColor(this, false))
                moneyButton3.setTextColor(getCorrectTextColor(this, true))
            }
            else -> {
                moneyButton1.background = getCorrectBackground(this, true)
                moneyButton2.background = getCorrectBackground(this, false)
                moneyButton3.background = getCorrectBackground(this, false)
                moneyButton1.setTextColor(getCorrectTextColor(this, true))
                moneyButton2.setTextColor(getCorrectTextColor(this, false))
                moneyButton3.setTextColor(getCorrectTextColor(this, false))
            }
        }
    }
}