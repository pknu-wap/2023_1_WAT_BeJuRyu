package com.jaino.review.review_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.dictionary.DrinksRepository
import com.jaino.model.dictionary.DrinkInfo
import com.jaino.model.review.ReviewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import usecase.review.GetReviewList
import javax.inject.Inject

@HiltViewModel
class ReviewListViewModel @Inject constructor(
    private val getReviewListUseCase: GetReviewList,
    private val drinksRepository: DrinksRepository
): ViewModel() {

    private val _reviewEvent = MutableSharedFlow<UiEvent>()
    val reviewEvent : SharedFlow<UiEvent> get() = _reviewEvent

    private val _reviewItem = MutableStateFlow<List<ReviewItem>>(emptyList())
    val reviewItem : SharedFlow<List<ReviewItem>> get() = _reviewItem
    private val _reviewState = MutableStateFlow<DrinkInfo>(DrinkInfo())
    val reviewState : StateFlow<DrinkInfo> get() =  _reviewState

    // 평점, 닉네임, 코멘트,
    fun getReviewList(drinkId : Long){
        viewModelScope.launch {
            getReviewListUseCase(drinkId)
                .onSuccess {
                    _reviewItem.value = it
                }
                .onFailure {
                    _reviewEvent.emit(UiEvent.Failure(it.message))
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
                    _reviewEvent.emit(UiEvent.Failure("주류 정보를 불러오는데 실패하였습니다."))
                }
        }
    }

    sealed class UiEvent{
        data class Failure(val message: String?) : UiEvent()
    }
}