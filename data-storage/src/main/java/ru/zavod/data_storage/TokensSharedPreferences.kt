package ru.zavod.data_storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zavod.app_core.model.Token
import javax.inject.Inject

class TokensSharedPreferences @Inject constructor(context: Context) {

    companion object {
        private const val TOKENS_PREFERENCES_NAME = "zavod_teplits_token"
        private const val ACCESS_TOKEN_KEY = "access_token"
        private const val REFRESH_TOKEN_KEY = "refresh_token"
        private const val USER_EXIST_KEY = "user_exist"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = TOKENS_PREFERENCES_NAME)
    }

    private val context = context.applicationContext

    private val tokenMutableStateFlow = MutableStateFlow<Token?>(value = null)
    val tokenStateFlow = tokenMutableStateFlow.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.data.collect { preferences ->
                tokenMutableStateFlow.value = extractToken(preferences = preferences)
            }
        }
    }

    fun saveToken(token: Token) {
        CoroutineScope(Dispatchers.IO).launch {
            val accessTokenKey = stringPreferencesKey(name = ACCESS_TOKEN_KEY)
            val refreshTokenKey = stringPreferencesKey(name = REFRESH_TOKEN_KEY)
            val userExistKey = booleanPreferencesKey(name = USER_EXIST_KEY)
            context.dataStore.edit { preferences ->
                val access = token.access
                val refresh = token.refresh
                if (access != null && refresh != null) {
                    preferences[accessTokenKey] = access
                    preferences[refreshTokenKey] = refresh
                    preferences[userExistKey] = token.userExist
                }
            }
        }
    }

    fun getToken(): Token? {
        return tokenStateFlow.value
    }

    fun removeToken() {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }

    private fun extractToken(preferences: Preferences): Token? {
        val accessToken = getStringValue(key = ACCESS_TOKEN_KEY, preferences = preferences)
        val refreshToken = getStringValue(key = REFRESH_TOKEN_KEY, preferences = preferences)
        val userExist = getBooleanValue(key = USER_EXIST_KEY, preferences = preferences)
        return Token(
            access = accessToken ?: return null,
            refresh = refreshToken ?: return null,
            userExist = userExist ?: false
        )
    }

    private fun getStringValue(key: String, preferences: Preferences): String? {
        return getValue(key = stringPreferencesKey(name = key), preferences = preferences)
    }

    private fun getBooleanValue(key: String, preferences: Preferences): Boolean? {
        return getValue(key = booleanPreferencesKey(name = key), preferences = preferences)
    }

    private fun <T> getValue(key: Preferences.Key<T>, preferences: Preferences): T? {
        return preferences[key]
    }
}