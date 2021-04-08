package com.jorfald.areyoubored.ui.helpers

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.jorfald.areyoubored.R

class ButtonHelper {
    companion object {
        fun getCorrectBackground(context: Context, isSelected: Boolean): Drawable? {
            return if (isSelected) {
                ContextCompat.getDrawable(context, R.drawable.selected_button)
            } else {
                ContextCompat.getDrawable(context, R.drawable.unselected_button)
            }
        }

        fun getCorrectTextColor(context: Context, isSelected: Boolean): Int {
            return if (isSelected) {
                ContextCompat.getColor(context, R.color.white)
            } else {
                ContextCompat.getColor(context, R.color.blue)
            }
        }
    }
}