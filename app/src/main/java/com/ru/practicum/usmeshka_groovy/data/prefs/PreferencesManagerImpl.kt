package com.ru.practicum.usmeshka_groovy.data.prefs

import android.content.SharedPreferences
import com.ru.practicum.usmeshka_groovy.domain.prefs.PreferencesManager

private const val KEY_WHO_USE = "key_WhoUse"

private const val KEY_NAME = "key_Name"

private const val KEY_SURNAME = "key_Surname"

private const val KEY_NOTIFICATIONS_IS_NEED = "key_NotificationsIsNeed"

private const val KEY_EMAIL = "key_email"

private const val KEY_REGISTRATION_COMPLETED = "Registration_completed"

class PreferencesManagerImpl(private val sharedPreferences: SharedPreferences) :
    PreferencesManager {

    override fun saveWhoUse(selectedId: Int, radioButtonText: String) {
        val editor = sharedPreferences.edit()
        if (selectedId != -1) {
            editor.putString(KEY_WHO_USE, radioButtonText)
        }
        editor.apply() // Применение изменений
    }

    override fun saveNameSurname(name: String, surname: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_SURNAME, surname)
        editor.apply()
    }

    override fun saveNotificationsIsNeed(selectedId: Int, radioButtonText: String) {
        val editor = sharedPreferences.edit()
        if (selectedId != -1) {
            editor.putString(KEY_NOTIFICATIONS_IS_NEED, radioButtonText)
        }
        editor.apply()
    }

    override fun saveEmail(email: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_EMAIL, email)
        editor.apply()
    }

    override fun setRegistrationCompleted(isCompleted: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_REGISTRATION_COMPLETED, isCompleted)
        editor.apply()
    }

    override fun getWhoUse(): String {
        return sharedPreferences.getString(KEY_WHO_USE, "") ?: ""
    }

    override fun getName(): String {
        return sharedPreferences.getString(KEY_NAME, "") ?: ""
    }

    override fun getSurname(): String {
        return sharedPreferences.getString(KEY_SURNAME, "") ?: ""
    }

    override fun getRegistrationCompleted(): Boolean {
        return sharedPreferences.getBoolean(KEY_REGISTRATION_COMPLETED, false)
    }
}