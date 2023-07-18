package com.jaino.datastore

import androidx.datastore.core.DataStore
import com.jaino.model.auth.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(
    private val userPreferencesStore : DataStore<UserPreferences>
){
    val userDataFlow: Flow<User> = userPreferencesStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception)
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }
        .map{
            User(it.userId, it.nickname)
        }

    suspend fun setNickname(nickname: String){
        userPreferencesStore.updateData { prefs ->
            prefs.copy{
                this.nickname = nickname
            }
        }
    }

    suspend fun setUserId(userId: Long){
        userPreferencesStore.updateData { prefs ->
            prefs.copy {
                this.userId = userId
            }
        }
    }
}