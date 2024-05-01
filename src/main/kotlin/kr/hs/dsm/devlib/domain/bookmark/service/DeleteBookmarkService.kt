package kr.hs.dsm.devlib.domain.bookmark.service

import kr.hs.dsm.devlib.common.util.SecurityUtil
import kr.hs.dsm.devlib.domain.book.exception.BookNotFoundException
import kr.hs.dsm.devlib.domain.book.persistence.repository.BookRepository
import kr.hs.dsm.devlib.domain.bookmark.persistence.repository.BookmarkRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteBookmarkService(
    private val bookRepository: BookRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    @Transactional
    fun execute(bookId: Long) {
        val user = SecurityUtil.getCurrentUser()
        val book = bookRepository.findByIdOrNull(bookId) ?: throw BookNotFoundException

        bookmarkRepository.findByUserAndBook(user, book)?.let {
            bookmarkRepository.delete(it)
        }
    }
}