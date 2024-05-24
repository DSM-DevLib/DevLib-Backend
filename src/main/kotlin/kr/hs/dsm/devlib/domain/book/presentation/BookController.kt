package kr.hs.dsm.devlib.domain.book.presentation

import kr.hs.dsm.devlib.domain.book.presentation.dto.RankType
import kr.hs.dsm.devlib.domain.book.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/book")
@RestController
class BookController(
    val bookService: BookService
) {
    @GetMapping
    fun queryBook(@RequestParam name: String?) =
        bookService.queryBook(name)

    @GetMapping("/rank")
    fun queryBookRank(@RequestParam type: RankType) =
        bookService.queryBookRank(type)

    @GetMapping("/{book-id}")
    fun queryBookDetail(@PathVariable("book-id") bookId: Long) =
        bookService.queryBookDetail(bookId)

    @GetMapping("/book/mark")
    fun queryMarkedBooks() = bookService.queryMarkedBooks()
}