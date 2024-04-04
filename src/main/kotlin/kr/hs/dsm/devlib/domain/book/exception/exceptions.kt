package kr.hs.dsm.devlib.domain.book.exception

import kr.hs.dsm.devlib.common.error.CustomException
import kr.hs.dsm.devlib.global.error.DomainErrorCode


object BookNotFoundException : CustomException(
    DomainErrorCode.BOOK_NOT_FOUND
)