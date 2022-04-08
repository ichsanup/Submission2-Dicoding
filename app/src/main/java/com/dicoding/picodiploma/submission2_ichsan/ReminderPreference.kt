package com.dicoding.picodiploma.submission2_ichsan

import android.content.Context

class ReminderPreference(context: Context) {

    companion object{
        const val PREFS_NAME = "prefs_name"
        private const val REMINDER = "isRemind"
    }

    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setReminder(value: Reminder){
        val editor = preference.edit()
        editor.putBoolean(REMINDER, value.isReminder)
        editor.apply()
    }

    fun getReminder():Reminder{
        val model = Reminder()
        model.isReminder = preference.getBoolean(REMINDER, false)
        return model
    }
}