package kr.hs.dsm.devlib.domain.user.service

import kr.hs.dsm.devlib.common.dto.TokenResponse
import kr.hs.dsm.devlib.common.util.SecurityUtil
import kr.hs.dsm.devlib.domain.user.exception.PasswordMismatchException
import kr.hs.dsm.devlib.domain.user.exception.UserAlreadyExist
import kr.hs.dsm.devlib.domain.user.exception.UserNotFound
import kr.hs.dsm.devlib.domain.book.persistence.Book
import kr.hs.dsm.devlib.domain.user.persistence.User
import kr.hs.dsm.devlib.domain.user.presentation.dto.*
import kr.hs.dsm.devlib.global.security.token.JwtGenerator
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kr.hs.dsm.devlib.domain.user.persistence.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtGenerator: JwtGenerator,
) {

    fun signIn(request: UserRequest): TokenResponse {
        val user = userRepository.findByAccountId(request.accountId) ?: throw UserNotFound
        if (!passwordEncoder.matches(request.password, user.password)) {
            throw PasswordMismatchException
        }
        return jwtGenerator.receiveToken(user.id)
    }

    fun signUp(request: UserRequest) {
        if (userRepository.existsByAccountId(request.accountId)) {
            throw UserAlreadyExist
        }
        userRepository.save(
            User(
                accountId = request.accountId,
                password = passwordEncoder.encode(request.password),
            )
        )
    }

    fun queryUserInfo(): UserInfoResponse {
        val user = SecurityUtil.getCurrentUser()

        return UserInfoResponse(
            accountId = user.accountId
        )
    }
}
