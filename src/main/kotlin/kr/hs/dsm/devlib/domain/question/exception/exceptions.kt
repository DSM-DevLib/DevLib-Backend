package kr.hs.dsm.devlib.domain.question.exception

import kr.hs.dsm.devlib.common.error.CustomException
import kr.hs.dsm.devlib.global.error.DomainErrorCode


object QuestionNotFoundException : CustomException(
    DomainErrorCode.QUESTION_NOT_FOUND
)