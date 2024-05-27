package kr.hs.dsm.devlib.domain.reaction.service

import kr.hs.dsm.devlib.common.util.SecurityUtil
import kr.hs.dsm.devlib.domain.reaction.persistence.Reaction
import kr.hs.dsm.devlib.domain.reaction.persistence.repository.ReactionRepository
import kr.hs.dsm.devlib.domain.reply.exception.ReplyNotFoundException
import kr.hs.dsm.devlib.domain.reply.persistence.repository.ReplyRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ReactionService(
    private val reactionRepository: ReactionRepository,
    private val replyRepository: ReplyRepository
) {
    fun create(replyId: Long) {
        val user = SecurityUtil.getCurrentUser()
        val reply = replyRepository.findByIdOrNull(replyId) ?: throw ReplyNotFoundException

        reactionRepository.findByUserAndReply(user, reply) ?: reactionRepository.save(
            Reaction(
                reply = reply,
                user = user
            )
        )
    }

    fun delete(replyId: Long) {
        val user = SecurityUtil.getCurrentUser()
        val reply = replyRepository.findByIdOrNull(replyId) ?: throw ReplyNotFoundException

        reactionRepository.findByUserAndReply(user, reply)?.let {
            reactionRepository.delete(it)
        }
    }
}