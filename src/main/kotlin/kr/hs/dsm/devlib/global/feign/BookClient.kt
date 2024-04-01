package kr.hs.dsm.devlib.global.feign

import kr.hs.dsm.devlib.domain.book.persistence.Book
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

const val COMMON: String = "코드 아키텍처 JS"
const val BACKEND: String = "스프링부트 SQL AWS node"
const val FRONTEND: String = "Typescript vue 리액트"
const val IOS: String = "Swift UIKit"
const val ANDROID: String = "Kotlin Android"
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
            description = it.description,
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