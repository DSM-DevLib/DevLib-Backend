package kr.hs.dsm.devlib.global.security.exception

import kr.hs.dsm.devlib.common.error.CustomException
import kr.hs.dsm.devlib.global.error.SecurityErrorCode

object InvalidTokenException : CustomException(
    SecurityErrorCode.INVALID_TOKEN
)
