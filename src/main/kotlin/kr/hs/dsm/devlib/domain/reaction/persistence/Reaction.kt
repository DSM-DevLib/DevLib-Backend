package kr.hs.dsm.devlib.domain.reaction.persistence

import kr.hs.dsm.devlib.domain.reply.persistence.Reply
import kr.hs.dsm.devlib.domain.user.persistence.User
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Reaction(
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    @JoinColumn(name = "reply_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val reply: Reply
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}