package kr.hs.dsm.devlib.domain.user.exception

import kr.hs.dsm.devlib.common.error.CustomException
import kr.hs.dsm.devlib.global.error.DomainErrorCode


object UserNotFound : CustomException(
    DomainErrorCode.USER_NOT_FOUND
)

object PasswordMismatchException : CustomException(
    DomainErrorCode.PASSWORD_MISMATCH
)

object UserAlreadyExist : CustomException(
    DomainErrorCode.ALREADY_USER_EXIST
)