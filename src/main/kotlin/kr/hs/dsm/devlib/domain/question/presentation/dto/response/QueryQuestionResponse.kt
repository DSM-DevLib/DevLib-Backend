package kr.hs.dsm.devlib.domain.question.presentation.dto.response

import java.time.LocalDateTime


data class QueryQuestionListResponse(
    val questions: List<QuestionDTO>
)
data class QuestionDTO(
    val username: String,
    val title: String,
    val createdDate: LocalDateTime
)