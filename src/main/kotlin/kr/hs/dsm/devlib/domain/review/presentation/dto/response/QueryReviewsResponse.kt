package kr.hs.dsm.devlib.domain.review.presentation.dto.response

import java.time.LocalDateTime

data class QueryReviewsResponse(
    val reviews: List<ReviewDTO>
)

data class ReviewDTO(
    val name: String,
    val id: Long,
    val createdAt: LocalDateTime,
    val score: Int,
    val mine: Boolean,
    val content: String,
)