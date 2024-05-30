package kr.hs.dsm.devlib.domain.review.service

import kr.hs.dsm.devlib.common.util.SecurityUtil
import kr.hs.dsm.devlib.domain.book.exception.BookNotFoundException
import kr.hs.dsm.devlib.domain.book.persistence.Review
import kr.hs.dsm.devlib.domain.book.persistence.repository.BookRepository
import kr.hs.dsm.devlib.domain.book.persistence.repository.ReviewRepository
import kr.hs.dsm.devlib.domain.review.exception.ReviewNotFoundException
import kr.hs.dsm.devlib.domain.review.presentation.dto.request.CreateReviewRequest
import kr.hs.dsm.devlib.domain.review.presentation.dto.request.UpdateReviewRequest
import kr.hs.dsm.devlib.domain.review.presentation.dto.response.QueryReviewsResponse
import kr.hs.dsm.devlib.domain.review.presentation.dto.response.ReviewDTO
import kr.hs.dsm.devlib.global.security.exception.NoPermissionException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val bookRepository: BookRepository
) {
    fun create(bookId: Long, request: CreateReviewRequest) {
        val book = bookRepository.findByIdOrNull(bookId) ?: throw BookNotFoundException
        val user = SecurityUtil.getCurrentUser()
        reviewRepository.save(
            Review(
                score = request.point,
                content = request.content,
                book = book,
                user = user
            )
        )
    }

    fun delete(reviewId: Long) {
        val user = SecurityUtil.getCurrentUser()
        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw ReviewNotFoundException

        if(user != review.user) throw NoPermissionException

        reviewRepository.delete(review)
    }

    fun update(reviewId: Long, request: UpdateReviewRequest) {
        val user = SecurityUtil.getCurrentUser()
        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw ReviewNotFoundException

        if (user != review.user) throw NoPermissionException

        review.content = request.content
        review.score = request.point
    }

    fun queryReviews(bookId: Long): QueryReviewsResponse {
        val user = SecurityUtil.getCurrentUser()
        val reviewDTOs = reviewRepository.findByBookId(bookId).map {
            ReviewDTO(
                name = it.user.accountId,
                id = it.id,
                createdAt = it.createdAt,
                content = it.content,
                score = it.score,
                mine = it.user == user
            )
        }
        return QueryReviewsResponse(reviewDTOs)
    }
}