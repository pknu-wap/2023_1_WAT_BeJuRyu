package model.validate

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)