package kr.hs.dsm.devlib.domain.reply.presentation.dto.request

data class UpdateReplyRequest(
    val content: String,
    val bookId: Long
)