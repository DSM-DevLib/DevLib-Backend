package kr.hs.dsm.devlib.domain.reaction.persistence.repository

import kr.hs.dsm.devlib.domain.reaction.persistence.Reaction
import kr.hs.dsm.devlib.domain.reply.persistence.Reply
import kr.hs.dsm.devlib.domain.user.persistence.User
import org.springframework.data.jpa.repository.JpaRepository

interface ReactionRepository : JpaRepository<Reaction, Long> {
    fun findByUserAndReply(user: User, reply: Reply): Reaction?
}