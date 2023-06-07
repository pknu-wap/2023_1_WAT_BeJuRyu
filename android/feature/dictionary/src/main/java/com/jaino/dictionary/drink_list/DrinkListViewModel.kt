package com.jaino.dictionary.drink_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.extensions.toTypedEng
import com.jaino.common.flow.EventFlow
import com.jaino.common.flow.MutableEventFlow
import com.jaino.common.flow.asEventFlow
import com.jaino.common.model.UiEvent
import com.jaino.common.model.UiState
import com.jaino.data.repository.dictionary.DrinksRepository
import com.jaino.model.dictionary.Drink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkListViewModel @Inject constructor(
    private val repository : DrinksRepository
): ViewModel() {

    private val _drinkListUiEvent : MutableEventFlow<UiEvent<Unit>> = MutableEventFlow()
    val drinkListUiEvent : EventFlow<UiEvent<Unit>> get() = _drinkListUiEvent.asEventFlow()

    private val _drinkListUiState : MutableStateFlow<UiState<List<Drink>>> = MutableStateFlow(UiState.Init)
    val drinkListUiState : StateFlow<UiState<List<Drink>>> get() = _drinkListUiState

    val searchWord : MutableStateFlow<String> = MutableStateFlow("")

    fun getDrinkListByWord(word: String){
        viewModelScope.launch {
            repository.getDrinkListByName(word)
                .onSuccess {
                    _drinkListUiState.value = UiState.Success(it)
                    searchWord.value = word
                }
                .onFailure {
                    _drinkListUiState.value = UiState.Failure(it.message)
                }
        }
    }
    fun getDrinkListByType(type: String){
        viewModelScope.launch {
            repository.getDrinkListByType(type.toTypedEng())
                .onSuccess {
                    _drinkListUiState.value = UiState.Success(it)
                }
                .onFailure {
                    _drinkListUiEvent.emit(UiEvent.Failure(it))
                }
        }
    }
}