package usecase.review

import com.jaino.data.repository.dictionary.DrinksRepository
import com.jaino.data.repository.review.ReviewRepository
import com.jaino.model.review.ReviewItem
import javax.inject.Inject

class GetReviewList @Inject constructor(
    private val drinksRepository : DrinksRepository,
    private val reviewRepository: ReviewRepository
){
    suspend operator fun invoke(drinkId: Long) : Result<List<ReviewItem>>{
        return runCatching {
            val reviewItem = mutableListOf<ReviewItem>()
            val drinkInfo = drinksRepository.getDrinkDataById(drinkId).getOrThrow()
            reviewRepository.getReviewList(drinkId).getOrThrow()
                .forEach{ drinkReview ->
                    reviewItem.add(ReviewItem(
                        drinkInfo.name,
                        drinkInfo.image,
                        drinkReview.reviewId,
                        drinkReview.nickname,
                        drinkReview.review,
                        drinkReview.score
                        )
                    )
                }
            reviewItem.toList()
        }.onFailure {
            Result.failure<GetReviewList>(it)
        }
    }
}