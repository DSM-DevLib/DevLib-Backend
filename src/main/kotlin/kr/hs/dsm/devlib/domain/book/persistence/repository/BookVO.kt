package kr.hs.dsm.devlib.domain.book.persistence.repository

import com.querydsl.core.annotations.QueryProjection
import kr.hs.dsm.devlib.domain.book.persistence.Book

class BookReviewCountDTO @QueryProjection constructor(
    id: Long,
    name: String,
    author: String,
    cover: String,
    description: String,
    viewCount: Int,
    link: String,
    discount: Int,
    val count: Long
): Book(id, name, author, cover, description, viewCount, link, discount)


class BookReviewAvgDTO @QueryProjection constructor(
    id: Long,
    name: String,
    author: String,
    cover: String,
    description: String,
    viewCount: Int,
    link: String,
    discount: Int,
    val avg: Double
): Book(id, name, author, cover, description, viewCount, link, discount)
