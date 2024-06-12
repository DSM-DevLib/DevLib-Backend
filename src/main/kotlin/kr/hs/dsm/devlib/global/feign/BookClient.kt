package kr.hs.dsm.devlib.global.feign

import kr.hs.dsm.devlib.domain.book.persistence.Book
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

const val COMMON: String = "프로그래머 코딩테스트 네트워크"
const val BACKEND: String = "스프링부트 AWS 백엔드"
const val FRONTEND: String = "Typescript 리액트 웹"
const val IOS: String = "앱"
const val ANDROID: String = "Kotlin 안드로이드"
val ALL = arrayOf(COMMON, BACKEND, FRONTEND, IOS, ANDROID).joinToString(" ")

class FeignBooksResponse(
    val items: List<FeignBookResponse>
) {
    fun toBooks() = items.map {
        Book(
            id = it.isbn.toLong(),
            name = it.title,
            author = it.author,
            cover = it.image,
            description = it.description.take(950),
            link = it.link,
            discount = it.discount.toInt()
        )
    }
}

class FeignBookResponse(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val discount: String,
    val publisher: String,
    val isbn: String,
    val description: String
)

@FeignClient(
    name = "deploy-client",
    url = "https://openapi.naver.com/v1/search/book_adv",
    configuration = [FeignConfig::class]
)
interface BookClient {

    @GetMapping
    fun getBooks(
        @RequestParam("d_isbn") isbn: Long? = null,
        @RequestParam("d_titl") name: String = ALL
    ): FeignBooksResponse
}
