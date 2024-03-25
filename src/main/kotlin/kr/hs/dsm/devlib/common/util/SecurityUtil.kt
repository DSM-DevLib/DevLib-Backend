package kr.hs.dsm.devlib.common.util

import kr.hs.dsm.devlib.global.security.principle.CustomDetails
import org.springframework.security.core.context.SecurityContextHolder

object SecurityUtil {
    fun getCurrentUser() =
        (SecurityContextHolder.getContext().authentication.principal as CustomDetails).user
}