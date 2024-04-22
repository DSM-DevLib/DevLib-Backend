package kr.hs.dsm.devlib.domain.question.service

import kr.hs.dsm.devlib.common.util.SecurityUtil
import kr.hs.dsm.devlib.domain.question.exception.QuestionNotFoundException
import kr.hs.dsm.devlib.domain.question.persistence.repository.QuestionRepository
import kr.hs.dsm.devlib.global.security.exception.NoPermissionException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteQuestionService(
    private val questionRepository: QuestionRepository
) {
    @Transactional
    fun execute(id: Long) {
        val user = SecurityUtil.getCurrentUser()
        val question = questionRepository.findByIdOrNull(id) ?: throw QuestionNotFoundException

        if(question.user != user) throw NoPermissionException
        questionRepository.delete(question)
    }
}