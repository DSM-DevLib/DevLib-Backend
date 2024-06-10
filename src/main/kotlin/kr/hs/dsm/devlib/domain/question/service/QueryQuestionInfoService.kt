package kr.hs.dsm.devlib.domain.question.service

import kr.hs.dsm.devlib.common.util.SecurityUtil
import kr.hs.dsm.devlib.domain.question.exception.QuestionNotFoundException
import kr.hs.dsm.devlib.domain.question.persistence.repository.QuestionRepository
import kr.hs.dsm.devlib.domain.question.presentation.dto.response.QueryQuestionInfoResponse
import kr.hs.dsm.devlib.domain.question.presentation.dto.response.ReplyDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryQuestionInfoService(
    private val questionRepository: QuestionRepository,
) {
    @Transactional(readOnly = true)
    fun execute(questionId: Long): QueryQuestionInfoResponse {
        val user = SecurityUtil.getCurrentUser()
        val question = questionRepository.findByIdOrNull(questionId) ?: throw QuestionNotFoundException

        val replyDTOs = question.replys.map {
            ReplyDTO(
                bookId = it.book.id,
                username = it.user.accountId,
                content = it.content,
                createdDate = it.createdAt,
                likeCount = it.reactions.size,
                mine = it.user == user,
                imageUrl = it.book.link,
                liked = it.reactions.any { like -> like.user == user }
            )
        }

        return QueryQuestionInfoResponse(
            id = question.id,
            title = question.title,
            content = question.content,
            author = question.user.accountId,
            replyDTOs,
            mine = question.user == user
        )
    }
}