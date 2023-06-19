package com.example.forecastmvvm.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import com.example.forecastmvvm.internal.UnitSystem

const val UNOT_SYSTEM = "UNIT_SYSTEM"

class UnitProviderImpl(context: Context) : UnitProvider {
    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getUnitSystem(): UnitSystem {
        val selectedName = preferences.getString("UNIT_SYSTEM", UnitSystem.IMPERIAL.name)
        return UnitSystem.valueOf(selectedName!!)
    }
}