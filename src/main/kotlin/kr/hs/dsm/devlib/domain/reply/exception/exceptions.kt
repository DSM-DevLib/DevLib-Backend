package kr.hs.dsm.devlib.domain.reply.exception

import kr.hs.dsm.devlib.common.error.CustomException
import kr.hs.dsm.devlib.global.error.DomainErrorCode


object ReplyNotFoundException : CustomException(
    DomainErrorCode.REPLY_NOT_FOUND
)