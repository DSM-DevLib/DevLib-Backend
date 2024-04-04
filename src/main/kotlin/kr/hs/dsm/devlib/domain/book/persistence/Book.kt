package kr.hs.dsm.devlib.domain.book.persistence

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "tbl_book")
@Entity
data class Book(

    @Id
    var id: Long = 0L,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val author: String,

    @Column(nullable = false, columnDefinition = "VARCHAR(500)")
    val cover: String,

    @Column(nullable = false, columnDefinition = "VARCHAR(1000)")
    val description: String,

    @Column(nullable = false)
    val viewCount: Int = 0,

    @Column(nullable = false)
    val link: String,

    @Column(nullable = false)
    val discount: Int = 0
)