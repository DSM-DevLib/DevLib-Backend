package kr.hs.dsm.devlib.global.error

import kr.hs.dsm.devlib.common.error.CustomException

object InternalServerError : CustomException(
    GlobalErrorCode.INTERNAL_SERVER_ERROR
)