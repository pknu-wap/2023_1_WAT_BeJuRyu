package com.jaino.analysis.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.flow.EventFlow
import com.jaino.common.flow.MutableEventFlow
import com.jaino.common.model.UiEvent
import com.jaino.data.repository.rank.RankRepository
import com.jaino.model.dictionary.Drink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RankRepository
) : ViewModel() {

    private val _homeUiEvent : MutableEventFlow<UiEvent<Unit>> = MutableEventFlow<UiEvent<Unit>>()
    val homeUiEvent : EventFlow<UiEvent<Unit>> get() = _homeUiEvent

    private val _homeUiState : MutableStateFlow<List<Drink>> = MutableStateFlow(emptyList())
    val homeUiState : StateFlow<List<Drink>> get() = _homeUiState

    private val _rankingTag : MutableStateFlow<String> = MutableStateFlow(MOST_REVIEWED)
    val rankingTag: StateFlow<String> get() = _rankingTag.asStateFlow()

    fun getRankingList(){
        when(_rankingTag.value){
            MOST_REVIEWED -> {
                getMostReviewedList()
            }
            HIGHEST_RATED -> {
                getHighestRatedList()
            }
        }
    }

    fun getHighestRatedList(){
        viewModelScope.launch {
            _rankingTag.value = HIGHEST_RATED
            repository.getHighestRatedList()
                .onSuccess {
                    _homeUiState.value = it
                }
                .onFailure {
                    _homeUiEvent.emit(UiEvent.Failure(it))
                }
        }
    }

    fun getMostReviewedList(){
        viewModelScope.launch {
            _rankingTag.value = MOST_REVIEWED
            repository.getMostReviewedList()
                .onSuccess {
                    _homeUiState.value = it
                }
                .onFailure {
                    _homeUiEvent.emit(UiEvent.Failure(it))
                }
        }
    }

    companion object{
        const val MOST_REVIEWED = "리뷰 많은 순"
        const val HIGHEST_RATED = "평점 높은 순"
    }
}