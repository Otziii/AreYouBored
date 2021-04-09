package com.jorfald.areyoubored.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jorfald.areyoubored.R
import com.jorfald.areyoubored.SHARED_PREF_FILE_NAME
import com.jorfald.areyoubored.SHARED_PREF_KEY_MONEY
import com.jorfald.areyoubored.SHARED_PREF_KEY_PEOPLE
import com.jorfald.areyoubored.helpers.ButtonHelper
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    private lateinit var peopleSpinner: Spinner
    private lateinit var moneyButton1: AppCompatButton
    private lateinit var moneyButton2: AppCompatButton
    private lateinit var moneyButton3: AppCompatButton

    private lateinit var sharedPreferences: SharedPreferences

    private var selectedPeople = 1
    private var selectedMoney = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        peopleSpinner = view.settings_people_spinner
        moneyButton1 = view.settings_money_button_1
        moneyButton2 = view.settings_money_button_2
        moneyButton3 = view.settings_money_button_3

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)

        initFromSharedPrefs()

        setUpSpinner()
        bindButtons()

        setCorrectMoneyButton(selectedMoney)
    }

    private fun initFromSharedPrefs() {
        selectedPeople = sharedPreferences.getInt(SHARED_PREF_KEY_PEOPLE, 1)
        selectedMoney = sharedPreferences.getInt(SHARED_PREF_KEY_MONEY, 1)
    }

    private fun setUpSpinner() {
        val peopleCountList = listOf(1, 2, 3, 4, 5)

        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, peopleCountList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        peopleSpinner.adapter = spinnerAdapter

        peopleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                if (peopleCountList[position] != selectedPeople) {
                    sharedPreferences.edit().putInt(
                        SHARED_PREF_KEY_PEOPLE,
                        peopleCountList[position]
                    ).apply()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        peopleSpinner.setSelection(peopleCountList.indexOf(selectedPeople))
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
        if (selectedButton != selectedMoney) {
            sharedPreferences.edit().putInt(
                SHARED_PREF_KEY_MONEY,
                selectedButton
            ).apply()
        }

        when (selectedButton) {
            2 -> {
                moneyButton1.background = ButtonHelper.getCorrectBackground(requireContext(), false)
                moneyButton2.background = ButtonHelper.getCorrectBackground(requireContext(), true)
                moneyButton3.background = ButtonHelper.getCorrectBackground(requireContext(), false)
                moneyButton1.setTextColor(ButtonHelper.getCorrectTextColor(requireContext(), false))
                moneyButton2.setTextColor(ButtonHelper.getCorrectTextColor(requireContext(), true))
                moneyButton3.setTextColor(ButtonHelper.getCorrectTextColor(requireContext(), false))
            }
            3 -> {
                moneyButton1.background = ButtonHelper.getCorrectBackground(requireContext(), false)
                moneyButton2.background = ButtonHelper.getCorrectBackground(requireContext(), false)
                moneyButton3.background = ButtonHelper.getCorrectBackground(requireContext(), true)
                moneyButton1.setTextColor(ButtonHelper.getCorrectTextColor(requireContext(), false))
                moneyButton2.setTextColor(ButtonHelper.getCorrectTextColor(requireContext(), false))
                moneyButton3.setTextColor(ButtonHelper.getCorrectTextColor(requireContext(), true))
            }
            else -> {
                moneyButton1.background = ButtonHelper.getCorrectBackground(requireContext(), true)
                moneyButton2.background = ButtonHelper.getCorrectBackground(requireContext(), false)
                moneyButton3.background = ButtonHelper.getCorrectBackground(requireContext(), false)
                moneyButton1.setTextColor(ButtonHelper.getCorrectTextColor(requireContext(), true))
                moneyButton2.setTextColor(ButtonHelper.getCorrectTextColor(requireContext(), false))
                moneyButton3.setTextColor(ButtonHelper.getCorrectTextColor(requireContext(), false))
            }
        }
    }
}