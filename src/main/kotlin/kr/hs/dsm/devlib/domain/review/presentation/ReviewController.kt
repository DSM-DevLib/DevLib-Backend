package kr.hs.dsm.devlib.domain.review.presentation

import kr.hs.dsm.devlib.domain.review.presentation.dto.request.CreateReviewRequest
import kr.hs.dsm.devlib.domain.review.presentation.dto.request.UpdateReviewRequest
import kr.hs.dsm.devlib.domain.review.presentation.dto.response.QueryReviewsResponse
import kr.hs.dsm.devlib.domain.review.service.ReviewService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/review")
class ReviewController(
    private val reviewService: ReviewService
) {
    @PostMapping("/{book-id}")
    fun create(@PathVariable("book-id") bookId: Long, @RequestBody request: CreateReviewRequest) {
        reviewService.create(bookId, request)
    }

    @PatchMapping("/{review-id}")
    fun update(@PathVariable("review-id") reviewId: Long, @RequestBody request: UpdateReviewRequest) {
        reviewService.update(reviewId, request)
    }

    @GetMapping("/{book-id")
    fun queryReviews(@PathVariable("book-id") bookId: Long): QueryReviewsResponse {
        return reviewService.queryReviews(bookId)
    }
}