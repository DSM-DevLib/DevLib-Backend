package kr.hs.dsm.devlib.domain.book.service

import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.hs.dsm.devlib.common.util.SecurityUtil
import kr.hs.dsm.devlib.domain.book.exception.BookNotFoundException
import kr.hs.dsm.devlib.domain.book.persistence.QBook.book
import kr.hs.dsm.devlib.domain.book.persistence.QReview.review
import kr.hs.dsm.devlib.domain.book.persistence.repository.BookRepository
import kr.hs.dsm.devlib.domain.book.persistence.repository.ReviewRepository
import kr.hs.dsm.devlib.domain.book.presentation.dto.BookDetailResponse
import kr.hs.dsm.devlib.domain.book.presentation.dto.BookRankResponse
import kr.hs.dsm.devlib.domain.book.presentation.dto.BookRanksResponse
import kr.hs.dsm.devlib.domain.book.presentation.dto.BookResponse
import kr.hs.dsm.devlib.domain.book.persistence.repository.BookReviewCountDTO
import kr.hs.dsm.devlib.domain.book.persistence.repository.QBookReviewAvgDTO
import kr.hs.dsm.devlib.domain.book.persistence.repository.QBookReviewCountDTO
import kr.hs.dsm.devlib.domain.book.presentation.dto.BooksResponse
import kr.hs.dsm.devlib.domain.book.presentation.dto.RankType
import kr.hs.dsm.devlib.domain.bookmark.persistence.repository.BookmarkRepository
import kr.hs.dsm.devlib.global.feign.BookClient
import org.jsoup.Jsoup
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class BookService(
    val bookClient: BookClient,
    val bookRepository: BookRepository,
    val reviewRepository: ReviewRepository,
    val bookmarkRepository: BookmarkRepository
) {
    fun queryBook(name: String?): BooksResponse {
        val books = (name?.let { bookClient.getBooks(name = name) } ?: bookClient.getBooks())
            .toBooks()
            .also { bookRepository.saveAll(it) }
        val reviews = reviewRepository.findByBookIdIn(books.map { it.id }).groupBy { it.book.id }
        return BooksResponse(
            books = books.map {
                BookResponse(
                    id = it.id,
                    name = it.name,
                    author = it.author,
                    cover = it.cover,
                    score = reviews[it.id]?.map { it.score }?.average() ?: 0.0,
                    reviewCount = reviews[it.id]?.size ?: 0,
                )
            }
        )
    }

    fun queryBookRank(type: RankType): BookRanksResponse {

        val books = when (type) {
            RankType.VIEW -> bookRepository.findTop20OrderByViewCount()
            RankType.REVIEW -> bookRepository.findTop20OrderByReviewCount()
            RankType.SCORE -> bookRepository.findTop20OrderByReviewScore()
        }
        val reviews = reviewRepository.findByBookIdIn(books.map { it.id }).groupBy { it.book.id }
        return BookRanksResponse(
            books = books.mapIndexed { idx, it ->
                BookRankResponse(
                    rank = idx + 1,
                    id = it.id,
                    name = it.name,
                    author = it.author,
                    cover = it.cover,
                    score = reviews[it.id]?.map { it.score }?.average() ?: 0.0,
                    reviewCount = reviews[it.id]?.size ?: 0,
                )
            }
        )
    }

    fun queryBookDetail(bookId: Long): BookDetailResponse {
        val book = bookRepository.findByIdOrNull(bookId) ?: let {
            val b = bookClient.getBooks(bookId).toBooks()
            if (b.isNotEmpty()) b[0] else null
        } ?: throw BookNotFoundException

        val rawData = Jsoup.connect(book.link).get()
        val elements = rawData.select(".paperBookSellerItem_main_info__pFtkk")
        val (price, purchaseSite, purchaseUrl) = if (elements.isNotEmpty()) let {
            val child = elements[0].children()
            Triple(
                child.select(".paperBookSellerItem_price__PCL4n")
                    .text()
                    .replace("[^\\d]".toRegex(), "")
                    .toInt(),
                child.select(".paperBookSellerItem_link_seller__24uIB")
                    .attr("href")
                    .replace("판매처 바로가기", "")
                    .replace("\n", ""),
                child.select(".paperBookSellerItem_mall_name__s6fGi")
                    .text()
                    .replace("네이버페이", "")
            )
        } else Triple(null, null, null)

        val user = SecurityUtil.getCurrentUser()

        return book.run {
            BookDetailResponse(
                id = id,
                name = name,
                author = author,
                cover = cover,
                description = description,
                price = price ?: discount,
                purchaseSite = purchaseSite ?: "네이버도서",
                purchaseUrl = purchaseUrl ?: link,
                isMarked = bookmarkRepository.findByUserAndBook(user, book) != null
            )
        }
    }
}