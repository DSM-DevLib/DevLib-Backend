package kr.hs.dsm.devlib.domain.reply.persistence

import kr.hs.dsm.devlib.domain.book.persistence.Book
import kr.hs.dsm.devlib.domain.book.persistence.QBook
import kr.hs.dsm.devlib.domain.question.persistence.Question
import kr.hs.dsm.devlib.domain.reaction.persistence.Reaction
import kr.hs.dsm.devlib.domain.user.persistence.User
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Table(name = "tbl_answer")
@Entity
class Reply(
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    var content: String,

    @JoinColumn(name = "book_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var book: Book,

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    @JoinColumn(name = "question_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val question: Question,

    @OneToMany(mappedBy = "reply")
    val reactions: List<Reaction> = ArrayList()
    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
