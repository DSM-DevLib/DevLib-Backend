package kr.hs.dsm.devlib.domain.book.presentation.dto

class BookResponse(
    val id: Long,
    val name: String,
    val author: String,
    val cover: String,
    val score: Double,
    val reviewCount: Int
)
class BooksResponse(val books: List<BookResponse>)

class BookRankResponse(
    val rank: Int,
    val id: Long,
    val name: String,
    val author: String,
    val cover: String
)
class BookRanksResponse(val books: List<BookRankResponse>)

class BookDetailResponse(
    val id: Long,
    val name: String,
    val author: String,
    val cover: String,
    val description: String,
    val price: Int,
    val purchaseSite: String,
    val purchaseUrl: String,
    val isMarked: Boolean = false
)