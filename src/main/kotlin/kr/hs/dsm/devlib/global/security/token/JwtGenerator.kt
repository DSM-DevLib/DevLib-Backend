package kr.hs.dsm.devlib.global.security.token

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import kr.hs.dsm.devlib.global.security.SecurityProperties
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID
import kr.hs.dsm.devlib.common.dto.TokenResponse

@Component
class JwtGenerator(
    private val securityProperties: SecurityProperties
){

    fun receiveToken(userId: Long) = TokenResponse(
        accessToken = generateAccessToken(userId)
    )

    private fun generateAccessToken(userId: Long) =
        Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, securityProperties.secretKey)
            .setHeaderParam(Header.JWT_TYPE, JwtProperties.ACCESS)
            .setId(userId.toString())
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.accessExp * 1000))
            .compact()
}
