package kr.hs.dsm.devlib.domain.user.presentation.dto

import javax.validation.constraints.NotBlank

data class UserRequest(
    @field:NotBlank
    val accountId: String,
    @field:NotBlank
    val password: String
)