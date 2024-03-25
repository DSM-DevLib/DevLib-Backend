package kr.hs.dsm.devlib.global.security.exception

import kr.hs.dsm.devlib.common.error.CustomException
import kr.hs.dsm.devlib.global.error.SecurityErrorCode

object UnexpectedTokenException : CustomException(
    SecurityErrorCode.UNEXPECTED_TOKEN
)
