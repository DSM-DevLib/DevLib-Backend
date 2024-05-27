package kr.hs.dsm.devlib.domain.reply.presentation.dto.request

data class CreateReplyRequest(
    val content: String,
    val bookId: Long
)