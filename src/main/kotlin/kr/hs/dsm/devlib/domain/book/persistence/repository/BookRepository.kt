package kr.hs.dsm.devlib.domain.book.persistence.repository

import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import kr.hs.dsm.devlib.domain.book.persistence.Book
import kr.hs.dsm.devlib.domain.book.persistence.QBook
import kr.hs.dsm.devlib.domain.book.persistence.QBook.book
import kr.hs.dsm.devlib.domain.book.persistence.QReview
import kr.hs.dsm.devlib.domain.book.persistence.QReview.review
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface BookRepository : CrudRepository<Book, Long>, CustomBookRepository {
    fun findAllByIdIn(idList: List<Long>): List<Book>
}

interface CustomBookRepository {
    fun findTop20OrderByViewCount(): List<Book>
    fun findTop20OrderByReviewCount(): List<Book>
    fun findTop20OrderByReviewScore(): List<Book>
}

@Repository
class CustomBookRepositoryImpl(
    val queryFactory: JPAQueryFactory
): CustomBookRepository {

    val limit: Long = 20

    override fun findTop20OrderByViewCount(): List<Book> {
        return queryFactory.selectFrom(book)
            .orderBy(book.viewCount.desc())
            .limit(limit)
            .fetch()
    }

    override fun findTop20OrderByReviewCount(): List<Book> {
        val aliasQuantity = Expressions.numberPath(Long::class.java, "quantity")
        return queryFactory.select(
            QBookReviewCountDTO(
                /* id = */ book.id,
                /* name = */ book.name,
                /* author = */ book.author,
                /* cover = */ book.cover,
                /* description = */ book.description,
                /* viewCount = */ book.viewCount,
                /* link = */ book.link,
                /* discount = */ book.discount,
                /* count = */ review.user.id.count().`as`(aliasQuantity)
            )
        )
            .from(book)
            .innerJoin(review).on(book.id.eq(review.book.id))
            .groupBy(book.id)
            .orderBy(aliasQuantity.desc())
            .limit(limit)
            .fetch()
    }

    override fun findTop20OrderByReviewScore(): List<Book> {
        val aliasAvg = Expressions.numberPath(Double::class.java, "mean")
        return queryFactory.select(
            QBookReviewAvgDTO(
                /* id = */ book.id,
                /* name = */ book.name,
                /* author = */ book.author,
                /* cover = */ book.cover,
                /* description = */ book.description,
                /* viewCount = */ book.viewCount,
                /* link = */ book.link,
                /* discount = */ book.discount,
                /* avg = */ review.score.avg().`as`(aliasAvg)
            )
        )
            .from(book)
            .innerJoin(review).on(book.id.eq(review.book.id))
            .groupBy(book.id)
            .orderBy(aliasAvg.desc())
            .limit(limit)
            .fetch()
    }
}