package kr.hs.dsm.devlib.domain.book.persistence

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import kr.hs.dsm.devlib.domain.user.persistence.User
import java.time.LocalDateTime

@Table(name = "tbl_review")
@Entity
data class Review(
    @Column(nullable = false)
    var score: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", columnDefinition = "BIGINT", nullable = false)
    var book: Book,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT", nullable = false)
    var user: User,

    @Column(nullable = false)
    var content: String,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}