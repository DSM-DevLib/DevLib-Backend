package kr.hs.dsm.devlib.domain.book.persistence.repository

import kr.hs.dsm.devlib.domain.book.persistence.Book
import kr.hs.dsm.devlib.domain.book.persistence.Review
import org.springframework.data.repository.CrudRepository

interface ReviewRepository : CrudRepository<Review, Long> {
    fun findByBookId(bookId: Long): List<Review>
    fun findByBookIdIn(bookIds: List<Long>): List<Review>
}