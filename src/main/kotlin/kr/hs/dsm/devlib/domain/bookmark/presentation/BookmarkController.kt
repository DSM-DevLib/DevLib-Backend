package kr.hs.dsm.devlib.domain.bookmark.presentation

import kr.hs.dsm.devlib.domain.bookmark.service.CreateBookmarkService
import kr.hs.dsm.devlib.domain.bookmark.service.DeleteBookmarkService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookmarkController(
    private val createBookmarkService: CreateBookmarkService,
    private val deleteBookmarkService: DeleteBookmarkService
) {
    @PostMapping("/{book-id}/mark")
    fun createBookmark(@PathVariable("book-id")bookId: Long) {
        createBookmarkService.execute(bookId)
    }

    @DeleteMapping("/{book-id}/mark")
    fun deleteBookmark(@PathVariable("book-id")bookId: Long) {
        deleteBookmarkService.execute(bookId)
    }
}