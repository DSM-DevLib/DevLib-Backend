package kr.hs.dsm.devlib.domain.book.persistence.repository

import kr.hs.dsm.devlib.domain.book.persistence.Book
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Long> {
}