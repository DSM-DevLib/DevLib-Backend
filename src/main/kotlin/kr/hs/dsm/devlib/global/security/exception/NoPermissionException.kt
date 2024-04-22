package kr.hs.dsm.devlib.global.security.exception

import kr.hs.dsm.devlib.common.error.CustomException
import kr.hs.dsm.devlib.global.error.SecurityErrorCode

object NoPermissionException : CustomException(
    SecurityErrorCode.NO_PERMISSION
)