package com.example.afinal.setting.data.datasource

import androidx.datastore.preferences.core.Preferences

data class UserPreferences(val workingHoursWeekday: Int?,
                           val workingHoursWeekend: Int?,
                           val notificationSwitch: Boolean?,
                           val notificationTimeMorning: Int?,
                           val notificationTimeEvening: Int?,
                           val wordSize: Int?,
                           val darkModeSwitch: Boolean?)
