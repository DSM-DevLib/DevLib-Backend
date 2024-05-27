package kr.hs.dsm.devlib.domain.reply.persistence.repository

import kr.hs.dsm.devlib.domain.reply.persistence.Reply
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository : JpaRepository<Reply, Long> {
}