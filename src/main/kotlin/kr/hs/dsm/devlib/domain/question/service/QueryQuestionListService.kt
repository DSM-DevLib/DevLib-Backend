package kr.hs.dsm.devlib.domain.question.service

import kr.hs.dsm.devlib.domain.question.persistence.repository.QuestionRepository
import kr.hs.dsm.devlib.domain.question.presentation.dto.response.QueryQuestionListResponse
import kr.hs.dsm.devlib.domain.question.presentation.dto.response.QuestionDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryQuestionListService(
    private val questionRepository: QuestionRepository
) {
    @Transactional(readOnly = true)
    fun execute(): QueryQuestionListResponse {
        val questionDTOs = questionRepository.findAll().map {
            QuestionDTO(
                title = it.title,
                username = it.user.accountId,
                createdDate = it.createdAt,
                questionId = it.id
            )
        }
        return QueryQuestionListResponse(
            questionDTOs
        )
    }
}