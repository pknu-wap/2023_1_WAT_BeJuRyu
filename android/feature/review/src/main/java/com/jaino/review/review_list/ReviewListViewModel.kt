package com.jaino.review.review_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.review.ReviewRepository
import com.jaino.model.review.DrinkReview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewListViewModel @Inject constructor(
    private val repository : ReviewRepository
): ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent : MutableSharedFlow<UiEvent> get() = _uiEvent

    // 평점, 닉네임, 코멘트,
    fun getReviewList(drinkId : Long){
        viewModelScope.launch {
            repository.getReviewList(drinkId)
                .onSuccess {
                    _uiEvent.emit(UiEvent.Success(it))
                }
                .onFailure {
                    _uiEvent.emit(UiEvent.Failure(it.message))
                }
        }
    }

    sealed class UiEvent{
        data class Failure(val message: String?) : UiEvent()
        data class Success(val data: List<DrinkReview>) : UiEvent()
    }
}