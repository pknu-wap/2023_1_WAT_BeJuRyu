package usecase.review

import com.jaino.data.repository.dictionary.DrinksRepository
import com.jaino.data.repository.review.ReviewRepository
import model.review.ReviewModel
import javax.inject.Inject

class GetReviewList @Inject constructor(
    private val drinksRepository : DrinksRepository,
    private val reviewRepository: ReviewRepository
){
    suspend operator fun invoke(drinkId: Long) : Result<List<ReviewModel>>{
        return runCatching {
            val reviewItem = mutableListOf<ReviewModel>()
            val drinkInfo = drinksRepository.getDrinkDataById(drinkId).getOrThrow()
            reviewRepository.getReviewList(drinkId).getOrThrow()
                .forEach{ drinkReview ->
                    reviewItem.add(
                        ReviewModel(
                            reviewId = drinkInfo.id,
                            drinkName = drinkInfo.name,
                            image = drinkInfo.image,
                            nickname = drinkReview.nickname,
                            comment = drinkReview.comment,
                            score = drinkReview.score,
                            date = drinkReview.date
                        )
                    )
                }
            reviewItem.toList()
        }.onFailure {
            Result.failure<GetReviewList>(it)
        }
    }
}