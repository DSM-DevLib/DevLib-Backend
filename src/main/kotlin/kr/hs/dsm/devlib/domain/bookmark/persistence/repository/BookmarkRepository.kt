package kr.hs.dsm.devlib.domain.bookmark.persistence.repository

import kr.hs.dsm.devlib.domain.book.persistence.Book
import kr.hs.dsm.devlib.domain.bookmark.persistence.Bookmark
import kr.hs.dsm.devlib.domain.user.persistence.User
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkRepository : JpaRepository<Bookmark, Long> {
    fun findByUserAndBook(user: User, book: Book): Bookmark?
    fun findAllByUser(user: User): List<Bookmark>
}