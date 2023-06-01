package com.jaino.review.write_review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.extensions.toDateTime
import com.jaino.data.repository.review.ReviewRepository
import com.jaino.data.repository.user.LocalUserRepository
import com.jaino.data.model.review.ReviewRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteReviewViewModel @Inject constructor(
    private val repository : ReviewRepository,
    private val userRepository: LocalUserRepository
): ViewModel() {

    val reviewContent : MutableStateFlow<String> = MutableStateFlow("")
    val ratingCount : MutableStateFlow<Float> = MutableStateFlow(0.0f)

    private val _uiEvent : MutableSharedFlow<UiEvent> = MutableSharedFlow<UiEvent>()
    val uiEvent : SharedFlow<UiEvent> get() = _uiEvent

    fun postReview(drinkId : Long){
        viewModelScope.launch {
            repository.postReview(drinkId,
                ReviewRequest(userRepository.getUserId(), reviewContent.value,
                    ratingCount.value.toInt(), System.currentTimeMillis().toDateTime())
            )
                .onSuccess {
                    _uiEvent.emit(UiEvent.Success)
                }
                .onFailure {
                    _uiEvent.emit(UiEvent.Failure(it.message))
                }
        }
    }

    sealed class UiEvent{
        data class Failure(val message : String?) : UiEvent()
        object Success : UiEvent()
    }
}