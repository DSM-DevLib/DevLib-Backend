package kr.hs.dsm.devlib.domain.question.persistence

import kr.hs.dsm.devlib.domain.reply.persistence.Reply
import kr.hs.dsm.devlib.domain.user.persistence.User
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Question(
    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @OneToMany(mappedBy = "question")
    val replys: List<Reply> = ArrayList()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}