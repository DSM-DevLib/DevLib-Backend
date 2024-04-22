package kr.hs.dsm.devlib.domain.question.presentation.dto.request

import javax.validation.constraints.NotNull

data class CreateQuestionRequest(
    @field:NotNull(message = "title is not null")
    val title: String,

    @field:NotNull(message = "content is not null")
    val content: String
)