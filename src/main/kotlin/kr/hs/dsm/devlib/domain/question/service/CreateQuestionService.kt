package kr.hs.dsm.devlib.domain.question.service

import kr.hs.dsm.devlib.common.util.SecurityUtil
import kr.hs.dsm.devlib.domain.question.persistence.Question
import kr.hs.dsm.devlib.domain.question.persistence.repository.QuestionRepository
import kr.hs.dsm.devlib.domain.question.presentation.dto.request.CreateQuestionRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateQuestionService(
    private val questionRepository: QuestionRepository
) {
    @Transactional
    fun execute(request: CreateQuestionRequest) {
        val user = SecurityUtil.getCurrentUser()
        questionRepository.save(
            Question(
                title = request.title,
                content = request.content,
                user = user
            )
        )
    }
}