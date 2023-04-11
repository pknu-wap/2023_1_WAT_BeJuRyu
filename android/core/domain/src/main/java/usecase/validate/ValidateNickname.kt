package usecase.validate

import model.validate.ValidationResult

class ValidateNickname {
    operator fun invoke(nickname: String): ValidationResult {
        if(nickname.length !in 2..9){
            return ValidationResult(
                successful = false,
                errorMessage = "이름은 2글자 이상 9글자 이하로 입력해주세요."
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}