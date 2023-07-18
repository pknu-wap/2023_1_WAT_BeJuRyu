package com.jaino.review.review_input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.extensions.toDateTime
import com.jaino.common.flow.EventFlow
import com.jaino.common.flow.MutableEventFlow
import com.jaino.common.flow.asEventFlow
import com.jaino.common.model.UiEvent
import com.jaino.data.repository.review.ReviewRepository
import com.jaino.data.repository.user.LocalUserRepository
import com.jaino.data.model.review.ReviewRequest
import com.jaino.data.repository.dictionary.DrinksRepository
import com.jaino.model.dictionary.Drink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewInputViewModel @Inject constructor(
    private val repository : ReviewRepository,
    private val userRepository: LocalUserRepository,
    private val drinksRepository: DrinksRepository
): ViewModel() {

    private val _reviewInputEvent : MutableEventFlow<UiEvent<Unit>> = MutableEventFlow<UiEvent<Unit>>()
    val reviewInputEvent : EventFlow<UiEvent<Unit>> get() = _reviewInputEvent.asEventFlow()

    private val _drinkUiState = MutableStateFlow<Drink>(Drink())
    val drinkUiState : StateFlow<Drink> get() =  _drinkUiState

    val reviewContent : MutableStateFlow<String> = MutableStateFlow("")
    val ratingCount : MutableStateFlow<Float> = MutableStateFlow(0.0f)

    fun postReview(drinkId : Long){
        viewModelScope.launch {
            repository.postReview(
                drinkId,
                ReviewRequest(
                    userId = userRepository.getUserId().first(), comment = reviewContent.value,
                    score = ratingCount.value.toInt(), date = System.currentTimeMillis().toDateTime()
                )
            ).onSuccess {
                _reviewInputEvent.emit(UiEvent.Success(it))
            }.onFailure {
                _reviewInputEvent.emit(UiEvent.Failure(it))
            }
        }
    }

    fun getDrinkInfo(drinkId : Long){
        viewModelScope.launch {
            drinksRepository.getDrinkDataById(drinkId)
                .onSuccess { drinkData ->
                    _drinkUiState.value = drinkData
                }
                .onFailure {
                    _reviewInputEvent.emit(UiEvent.Failure(it))
                }
        }
    }
}