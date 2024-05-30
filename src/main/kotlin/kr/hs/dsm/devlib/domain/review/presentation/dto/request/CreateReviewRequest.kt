package kr.hs.dsm.devlib.domain.review.presentation.dto.request

data class CreateReviewRequest(
    val point: Int,
    val content: String
)
