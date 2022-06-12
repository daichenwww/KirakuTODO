package com.example.afinal.setting.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.afinal.setting.data.datasource.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*

private const val SETTING = "setting"

val Context.dataStore by preferencesDataStore(
    name = SETTING,
    produceMigrations = {
            context -> listOf(SharedPreferencesMigration(context, SETTING))
    }

)
 */


class UserPreferencesRepository(
    private val userPreferencesStore: DataStore<Preferences>
    ) {

    private object PreferenceKeys{
        val WORKING_HOURS_WEEKDAY = intPreferencesKey("workingHoursWeekday")
        val WORKING_HOURS_WEEKEND = intPreferencesKey("workingHoursWeekend")
        val NOTIFICATION_SWITCH = booleanPreferencesKey("notificationSwitch")
        val NOTIFICATION_TIME_MORNING = intPreferencesKey("notificationTimeMorning")
        val NOTIFICATION_TIME_EVENING = intPreferencesKey("notificationTimeEvening")
        val WORD_SIZE = intPreferencesKey("wordSize")
        val DARK_MODE_SWITCH = booleanPreferencesKey("darkModeSwitch")
    }

    val userPreferencesFlow: Flow<UserPreferences> = userPreferencesStore.data
        .map { preferences ->
            // workingHoursWeekday, defaulting to 2
            val workingHoursWeekday = preferences[PreferenceKeys.WORKING_HOURS_WEEKDAY]?:2
            // workingHoursWeekday, defaulting to 8
            val workingHoursWeekend = preferences[PreferenceKeys.WORKING_HOURS_WEEKEND]?:8
            // notificationSwitch, defaulting to true if not set:
            val notificationSwitch = preferences[PreferenceKeys.NOTIFICATION_SWITCH]?:true
            val notificationTimeMorning = preferences[PreferenceKeys.NOTIFICATION_TIME_MORNING]?:9
            val notificationTimeEvening = preferences[PreferenceKeys.NOTIFICATION_TIME_MORNING]?:18

            // wordSize, defaulting to 1 if not set:
            val wordSize = preferences[PreferenceKeys.WORD_SIZE]?:1
            // darkModeSwitch, defaulting to false if not set:
            val darkModeSwitch = preferences[PreferenceKeys.DARK_MODE_SWITCH]?:false
            UserPreferences(workingHoursWeekday, workingHoursWeekend, notificationSwitch, notificationTimeMorning, notificationTimeEvening, wordSize, darkModeSwitch)
        }



    suspend fun editWorkingHoursWeekday (newWorkingHoursWeekday: Int){
        userPreferencesStore.edit{ preferences->
            preferences[PreferenceKeys.WORKING_HOURS_WEEKDAY] = newWorkingHoursWeekday
        }
    }

    suspend fun editWorkingHoursWeekend (newWorkingHoursWeekend: Int){
        userPreferencesStore.edit{ preferences->
            preferences[PreferenceKeys.WORKING_HOURS_WEEKEND] = newWorkingHoursWeekend
        }
    }

    suspend fun editNotificationSwitch (newNotificationSwitch: Boolean){
        userPreferencesStore.edit{ preferences->
            preferences[PreferenceKeys.NOTIFICATION_SWITCH] = newNotificationSwitch
        }
    }
    suspend fun editNotificationTimeMorning (newNotificationTime_Morning: Int){
        userPreferencesStore.edit{ preferences->
            preferences[PreferenceKeys.NOTIFICATION_TIME_MORNING] = newNotificationTime_Morning
        }
    }
    suspend fun editNotificationTimeEvening (newNotificationTime_Evening: Int){
        userPreferencesStore.edit{ preferences->
            preferences[PreferenceKeys.NOTIFICATION_TIME_EVENING] = newNotificationTime_Evening
        }
    }

    suspend fun editWordSize (newWordSize: Int){
        userPreferencesStore.edit{ preferences->
            preferences[PreferenceKeys.WORD_SIZE] = newWordSize
        }
    }

    suspend fun editDarkMode (newDarkModeSwitch: Boolean){
        userPreferencesStore.edit{ preferences->
            preferences[PreferenceKeys.NOTIFICATION_SWITCH] = newDarkModeSwitch
        }
    }

}