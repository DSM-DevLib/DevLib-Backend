package kr.hs.dsm.devlib.domain.review.exception

import kr.hs.dsm.devlib.common.error.CustomException
import kr.hs.dsm.devlib.global.error.DomainErrorCode


object ReviewNotFoundException : CustomException(
    DomainErrorCode.REVIEW_NOT_FOUND
)