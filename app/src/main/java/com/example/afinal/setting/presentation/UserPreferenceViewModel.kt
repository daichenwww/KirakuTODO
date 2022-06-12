package com.example.afinal.setting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afinal.setting.data.datasource.UserPreferences
import com.example.afinal.setting.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPreferenceViewModel@Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel()  {

    val settings = userPreferencesRepository.userPreferencesFlow

    fun editWorkingHoursWeekday(newWorkingHoursWeekday: Int){
        viewModelScope.launch {
            userPreferencesRepository.editWorkingHoursWeekday(newWorkingHoursWeekday)
        }
    }
    fun editWorkingHoursWeekend(newWorkingHoursWeekend: Int){
        viewModelScope.launch {
            userPreferencesRepository.editWorkingHoursWeekend(newWorkingHoursWeekend)
        }
    }
    fun editNotificationSwitch (newNotificationSwitch: Boolean){
        viewModelScope.launch {
            userPreferencesRepository.editNotificationSwitch(newNotificationSwitch)
        }
    }
    fun editNotificationTimeMorning(newNotificationTime_Morning: Int){
        viewModelScope.launch {
            userPreferencesRepository.editNotificationTimeMorning(newNotificationTime_Morning)
        }
    }

    fun editNotificationTimeEvening(newNotificationTime_Evening: Int){
        viewModelScope.launch {
            userPreferencesRepository.editNotificationTimeEvening(newNotificationTime_Evening)
        }
    }

    fun editWordSize(newWordSize: Int){
        viewModelScope.launch {
            userPreferencesRepository.editWordSize(newWordSize)
        }
    }
    fun editDarkMode(newDarkMode:Boolean){
        viewModelScope.launch {
            userPreferencesRepository.editDarkMode(newDarkMode)
        }
    }

}