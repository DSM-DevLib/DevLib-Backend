package kr.hs.dsm.devlib.domain.question.presentation.dto.response

import java.time.LocalDateTime

data class QueryQuestionInfoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: String,
    val replyList: List<ReplyDTO>,
    val mine: Boolean
)

data class ReplyDTO(
    val createdDate: LocalDateTime,
    val username: String,
    val likeCount: Int,
    val content: String,
    val bookId: Long,
    val mine: Boolean
)