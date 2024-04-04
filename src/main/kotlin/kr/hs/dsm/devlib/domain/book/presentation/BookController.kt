package kr.hs.dsm.devlib.domain.book.presentation

import kr.hs.dsm.devlib.domain.book.exception.BookNotFoundException
import kr.hs.dsm.devlib.domain.book.persistence.Book
import kr.hs.dsm.devlib.domain.book.persistence.repository.BookRepository
import kr.hs.dsm.devlib.domain.book.persistence.repository.ReviewRepository
import kr.hs.dsm.devlib.domain.book.presentation.dto.BookDetailResponse
import kr.hs.dsm.devlib.domain.book.presentation.dto.BookResponse
import kr.hs.dsm.devlib.domain.book.presentation.dto.BooksResponse
import kr.hs.dsm.devlib.domain.book.service.BookService
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import kr.hs.dsm.devlib.global.feign.BookClient
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/book")
@RestController
class BookController(
    val bookService: BookService
) {
    @GetMapping
    fun queryBook() = bookService.queryBook()

    @GetMapping("/{book-id}")
    fun queryBookDetail(@PathVariable("book-id") bookId: Long) =
        bookService.queryBookDetail(bookId)
}