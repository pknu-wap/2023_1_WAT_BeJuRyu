package com.jaino.dictionary.drink_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.dictionary.DrinksRepository
import com.jaino.model.dictionary.DrinkData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkListViewModel @Inject constructor(
    private val repository : DrinksRepository
): ViewModel() {

    private val _dictUiState : MutableStateFlow<UiState> = MutableStateFlow(UiState.Init)
    val dictUiState : StateFlow<UiState> get() = _dictUiState

    init{
        getDrinksList()
    }

    private fun getDrinksList(){
        viewModelScope.launch {
            repository.getDrinkList()
                .onSuccess {
                    _dictUiState.value = UiState.Success(it)
                }
                .onFailure {
                    _dictUiState.value = UiState.Failure(it.message)
                }
        }
    }

    sealed class UiState {
        object Init : UiState()
        data class Success(val list : List<DrinkData>) : UiState()
        data class Failure(val message : String?) : UiState()
    }
}