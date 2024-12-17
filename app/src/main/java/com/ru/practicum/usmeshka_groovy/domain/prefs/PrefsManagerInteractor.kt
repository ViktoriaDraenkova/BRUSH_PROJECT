package com.ru.practicum.usmeshka_groovy.domain.prefs

interface PrefsManagerInteractor {
    fun saveWhoUse(selectedId: Int, radioButtonText: String)
    fun saveNameSurname(name: String, surname: String)
    fun saveNotificationsIsNeed(selectedId: Int, radioButtonText: String)
    fun saveEmail(email: String)
    fun setRegistrationCompleted(isCompleted: Boolean)
    fun getWhoUse(): String
    fun getName(): String
    fun getSurname(): String
    fun getRegistrationCompleted(): Boolean
}