package com.ru.practicum.usmeshka_groovy.domain.prefs.impl

import com.ru.practicum.usmeshka_groovy.domain.prefs.PreferencesManager
import com.ru.practicum.usmeshka_groovy.domain.prefs.PrefsManagerInteractor

class PrefsManagerInteractorImpl(val preferencesManager: PreferencesManager) :
    PrefsManagerInteractor {

    override fun saveWhoUse(selectedId: Int, radioButtonText: String) {
        preferencesManager.saveWhoUse(selectedId, radioButtonText)
    }

    override fun saveNameSurname(name: String, surname: String) {
        preferencesManager.saveNameSurname(name, surname)
    }

    override fun saveNotificationsIsNeed(selectedId: Int, radioButtonText: String) {
        preferencesManager.saveNotificationsIsNeed(selectedId, radioButtonText)
    }

    override fun saveEmail(email: String) {
        preferencesManager.saveEmail(email)
    }

    override fun setRegistrationCompleted(isCompleted: Boolean) {
        preferencesManager.setRegistrationCompleted(isCompleted)
    }

    override fun getWhoUse(): String {
        return preferencesManager.getWhoUse()
    }

    override fun getName(): String {
        return preferencesManager.getName()
    }

    override fun getSurname(): String {
        return preferencesManager.getSurname()
    }

    override fun getRegistrationCompleted(): Boolean {
        return preferencesManager.getRegistrationCompleted()
    }

}
