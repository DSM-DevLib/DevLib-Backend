package kr.hs.dsm.devlib.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import kr.hs.dsm.devlib.common.error.ErrorProperty
import kr.hs.dsm.devlib.global.error.ErrorResponse
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kr.hs.dsm.devlib.common.error.CustomException
import kr.hs.dsm.devlib.global.error.GlobalErrorCode

class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: CustomException) {
            errorToJson(e.errorProperty, response)
        } catch (e: Exception) {
            when (e.cause) {
                is CustomException -> {
                    errorToJson((e.cause as CustomException).errorProperty, response)
                }
                else -> {
                    e.printStackTrace()
                    errorToJson(GlobalErrorCode.INTERNAL_SERVER_ERROR, response)
                }
            }
        }
    }

    private fun errorToJson(errorProperty: ErrorProperty, response: HttpServletResponse) {
        response.status = errorProperty.status()
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(ErrorResponse.of(errorProperty)))
    }
}
