package com.jaino.dictionary.drink_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.extensions.toTypedEng
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

    private val _dictUiState : MutableStateFlow<UiState> = MutableStateFlow(UiState.Init)
    val dictUiState : StateFlow<UiState> get() = _dictUiState

    val searchWord : MutableStateFlow<String> = MutableStateFlow("")

    fun getDrinkListByWord(word: String){
        viewModelScope.launch {
            repository.getDrinkListByName(word)
                .onSuccess {
                    _dictUiState.value = UiState.Success(it)
                    searchWord.value = word
                }
                .onFailure {
                    _dictUiState.value = UiState.Failure(it.message)
                }
        }
    }
    fun getDrinkListByType(type: String){
        viewModelScope.launch {
            if(type.toTypedEng().isEmpty()){
                _dictUiState.value = UiState.Failure("해당 카테고리를 찾을 수 없습니다.")
                return@launch
            }
            repository.getDrinkListByType(type.toTypedEng())
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
        data class Success(val list : List<Drink>) : UiState()
        data class Failure(val message : String?) : UiState()
    }
}