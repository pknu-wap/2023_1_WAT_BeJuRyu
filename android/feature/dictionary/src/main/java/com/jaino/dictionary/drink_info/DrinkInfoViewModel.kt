package com.jaino.dictionary.drink_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.flow.EventFlow
import com.jaino.common.flow.MutableEventFlow
import com.jaino.common.flow.asEventFlow
import com.jaino.common.model.UiEvent
import com.jaino.data.repository.dictionary.DrinksRepository
import com.jaino.model.dictionary.Drink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkInfoViewModel @Inject constructor(
    private val repository: DrinksRepository
): ViewModel() {

    private val _drinkInfoEvent : MutableEventFlow<UiEvent<Unit>> = MutableEventFlow()
    val drinkInfoEvent : EventFlow<UiEvent<Unit>> get() = _drinkInfoEvent.asEventFlow()

    private val _drinkInfoUiState : MutableStateFlow<Drink> = MutableStateFlow(Drink())
    val drinkInfoUiState : StateFlow<Drink> get() = _drinkInfoUiState
    
    fun getDrinkData(id: Long){
        viewModelScope.launch {
            repository.getDrinkDataById(id)
                .onSuccess { drinkData ->
                    _drinkInfoUiState.value = drinkData
                }
                .onFailure {
                    _drinkInfoEvent.emit(UiEvent.Failure(it))
                }
        }
    }
}