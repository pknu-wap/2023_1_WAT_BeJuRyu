package com.jaino.review.review_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.flow.EventFlow
import com.jaino.common.flow.MutableEventFlow
import com.jaino.common.flow.asEventFlow
import com.jaino.common.model.UiEvent
import com.jaino.common.model.UiState
import com.jaino.data.repository.dictionary.DrinksRepository
import com.jaino.model.dictionary.Drink
import model.review.ReviewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import usecase.review.GetReviewList
import javax.inject.Inject

@HiltViewModel
class ReviewListViewModel @Inject constructor(
    private val getReviewListUseCase: GetReviewList,
    private val drinksRepository: DrinksRepository
): ViewModel() {

    private val _reviewEvent = MutableEventFlow<UiEvent<Unit>>()
    val reviewEvent : EventFlow<UiEvent<Unit>> get() = _reviewEvent.asEventFlow()

    private val _reviewItem = MutableStateFlow<UiState<List<ReviewModel>>>(UiState.Init)
    val reviewItem : StateFlow<UiState<List<ReviewModel>>> get() = _reviewItem
    private val _reviewState = MutableStateFlow<Drink>(Drink())
    val reviewState : StateFlow<Drink> get() =  _reviewState

    // 평점, 닉네임, 코멘트,
    fun getReviewList(drinkId : Long){
        viewModelScope.launch {
            getReviewListUseCase(drinkId)
                .onSuccess {
                    _reviewItem.value = UiState.Success(it)
                }
                .onFailure {
                    _reviewEvent.emit(UiEvent.Failure(it))
                }
        }
    }

    fun getDrinkInfo(drinkId : Long){
        viewModelScope.launch {
            drinksRepository.getDrinkDataById(drinkId)
                .onSuccess { drinkData ->
                    _reviewState.value = drinkData
                }
                .onFailure {
                    _reviewEvent.emit(UiEvent.Failure(it))
                }
        }
    }
}