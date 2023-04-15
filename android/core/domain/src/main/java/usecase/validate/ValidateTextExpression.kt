package usecase.validate

import model.validate.ValidationResult

class ValidateTextExpression {
    operator fun invoke(text: String): ValidationResult {
        if(text.length < 10){
            return ValidationResult(
                successful = false,
                errorMessage = "10글자 이상으로 작성해주세요!"
            )
        }
        val containsLetters= text.any { it.isLetter() }
        if(!containsLetters){
            return ValidationResult(
                successful = false,
                errorMessage = "한 글자이상 포함되어 있어야 합니다!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}