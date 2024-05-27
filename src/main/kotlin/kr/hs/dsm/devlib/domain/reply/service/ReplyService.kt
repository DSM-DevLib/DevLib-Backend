package kr.hs.dsm.devlib.domain.reply.service

import kr.hs.dsm.devlib.common.util.SecurityUtil
import kr.hs.dsm.devlib.domain.book.exception.BookNotFoundException
import kr.hs.dsm.devlib.domain.book.persistence.repository.BookRepository
import kr.hs.dsm.devlib.domain.question.exception.QuestionNotFoundException
import kr.hs.dsm.devlib.domain.question.persistence.repository.QuestionRepository
import kr.hs.dsm.devlib.domain.reply.exception.ReplyNotFoundException
import kr.hs.dsm.devlib.domain.reply.persistence.Reply
import kr.hs.dsm.devlib.domain.reply.persistence.repository.ReplyRepository
import kr.hs.dsm.devlib.domain.reply.presentation.dto.request.CreateReplyRequest
import kr.hs.dsm.devlib.domain.reply.presentation.dto.request.UpdateReplyRequest
import kr.hs.dsm.devlib.global.security.exception.NoPermissionException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.contracts.contract

@Transactional
@Service
class ReplyService(
    private val replyRepository: ReplyRepository,
    private val bookRepository: BookRepository,
    private val questionRepository: QuestionRepository
) {
    fun create(questionId: Long, request: CreateReplyRequest) {
        val user = SecurityUtil.getCurrentUser()
        val book = bookRepository.findByIdOrNull(request.bookId) ?: throw BookNotFoundException
        val question = questionRepository.findByIdOrNull(questionId) ?: throw QuestionNotFoundException

        replyRepository.save(
            Reply(
                book = book,
                user = user,
                question = question,
                content = request.content
            )
        )
    }

    fun update(replyId: Long, request: UpdateReplyRequest) {
        val user = SecurityUtil.getCurrentUser()
        val book = bookRepository.findByIdOrNull(request.bookId) ?: throw BookNotFoundException
        val reply = replyRepository.findByIdOrNull(replyId) ?: throw ReplyNotFoundException

        if(reply.user != user) throw NoPermissionException

        reply.book = book
        reply.content = request.content
    }

    fun delete(replyId: Long) {
        val user = SecurityUtil.getCurrentUser()
        val reply = replyRepository.findByIdOrNull(replyId) ?: throw ReplyNotFoundException

        if(reply.user != user) throw NoPermissionException

        replyRepository.delete(reply)
    }
}