package com.hyundai.myexperience.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.hyundai.myexperience.DATASTORE_USER
import com.hyundai.myexperience.IS_SIGNED
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_USER)

class UserLocalDataSource @Inject constructor(private val context: Context) {
    private val isSignedKey = booleanPreferencesKey(IS_SIGNED)

    suspend fun setIsSigned(isSigned: Boolean) {
        context.dataStore.edit {
            it[isSignedKey] = isSigned
        }
    }
    suspend fun getIsSigned(): Boolean = context.dataStore.data
        .map { preferences ->
            preferences[isSignedKey] ?: false
        }.first()
}