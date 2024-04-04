package kr.hs.dsm.devlib.domain.book.persistence

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table
import kr.hs.dsm.devlib.domain.user.persistence.User

@Table(name = "tbl_review")
@Entity
data class Review(

    @EmbeddedId
    val id: ReviewId,

    @Column(nullable = false)
    val score: Int,

    @MapsId("bookId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", columnDefinition = "BIGINT", nullable = false)
    var book: Book,

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT", nullable = false)
    var user: User,
) {
    constructor(score: Int, book: Book, user: User): this(
        id = ReviewId(book.id, user.id),
        score, book, user
    )
}

@Embeddable
data class ReviewId (
    @Column
    val bookId: Long,

    @Column
    val userId: Long
) : Serializable