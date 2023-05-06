package com.jaino.dictionary.drink_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.dictionary.DrinksRepository
import com.jaino.model.dictionary.DrinkData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkInfoViewModel @Inject constructor(
    private val repository: DrinksRepository
): ViewModel() {

    private val _drinkDataUiState : MutableStateFlow<DrinkData> = MutableStateFlow(DrinkData())
    val drinkDataUiState : StateFlow<DrinkData> get() = _drinkDataUiState

    private val _drinkInfoEvent : MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val drinkInfoEvent : SharedFlow<UiEvent> get() = _drinkInfoEvent
    
    fun getDrinkData(id: Long){
        viewModelScope.launch { 
            repository.getDrinkDataById(id)
                .onSuccess { drinkData -> 
                    _drinkDataUiState.value = drinkData
                }
                .onFailure {
                    _drinkInfoEvent.emit(UiEvent.Failure(it.message))
                }
        }
    }
    
    sealed class UiEvent{
        object Init : UiEvent()
        data class Failure(val message : String?) : UiEvent()
    }
}