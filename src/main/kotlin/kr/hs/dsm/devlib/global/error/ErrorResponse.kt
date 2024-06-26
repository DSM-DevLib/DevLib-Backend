package kr.hs.dsm.devlib.global.error

import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import kr.hs.dsm.devlib.common.error.ErrorProperty

data class ErrorResponse(
    val status: Int,
    val message: String,
    val code: String
) {

    companion object {
        fun of(errorProperty: ErrorProperty) = ErrorResponse(
            errorProperty.status(),
            errorProperty.message(),
            errorProperty.code()
        )

        fun of(e: BindingResult): ValidationErrorResponse {
            val errorMap = HashMap<String, String?>()

            for (error: FieldError in e.fieldErrors) {
                errorMap[error.field] = error.defaultMessage
            }

            return ValidationErrorResponse(
                status = GlobalErrorCode.BAD_REQUEST.status(),
                fieldError = errorMap
            )
        }
    }
}

data class ValidationErrorResponse(
    val status: Int,
    val fieldError: Map<String, String?>
)
