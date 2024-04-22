package kr.hs.dsm.devlib.domain.question.persistence.repository

import kr.hs.dsm.devlib.domain.question.persistence.Question
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Long> {
}