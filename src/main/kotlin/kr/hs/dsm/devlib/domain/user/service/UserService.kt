package kr.hs.dsm.devlib.domain.user.service

import kr.hs.dsm.devlib.common.dto.TokenResponse
import kr.hs.dsm.devlib.common.util.SecurityUtil
import kr.hs.dsm.devlib.domain.question.persistence.repository.AnswersRepository
import kr.hs.dsm.devlib.domain.question.persistence.dto.QuestionDetailDto
import kr.hs.dsm.devlib.domain.question.persistence.repository.QuestionSetsRepository
import kr.hs.dsm.devlib.domain.question.persistence.repository.QuestionsRepository
import kr.hs.dsm.devlib.domain.question.presentation.dto.QuestionSetListResponse
import kr.hs.dsm.devlib.domain.question.presentation.dto.UserQuestionListResponse
import kr.hs.dsm.devlib.domain.question.presentation.dto.UserQuestionResponse
import kr.hs.dsm.devlib.domain.user.exception.AttendanceNotFound
import kr.hs.dsm.devlib.domain.user.exception.PasswordMismatchException
import kr.hs.dsm.devlib.domain.user.exception.UserAlreadyExist
import kr.hs.dsm.devlib.domain.user.exception.UserNotFound
import kr.hs.dsm.devlib.domain.user.persistence.Attendance
import kr.hs.dsm.devlib.domain.user.persistence.User
import kr.hs.dsm.devlib.domain.user.persistence.repository.AttendanceRepository
import kr.hs.dsm.devlib.domain.user.presentation.dto.*
import kr.hs.dsm.devlib.global.security.token.JwtGenerator
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional
import kr.hs.dsm.devlib.domain.user.persistence.repository.UserRepository
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtGenerator: JwtGenerator,
) {

    fun signIn(request: UserSignInRequest): TokenResponse {
        val user = userRepository.findByAccountId(request.accountId)
            ?: throw UserNotFound

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw PasswordMismatchException
        }

        return jwtGenerator.receiveToken(user.id)
    }

    fun signUp(request: UserSignUpRequest) {
        if (userRepository.existsByAccountId(request.accountId)) {
            throw UserAlreadyExist
        }

        userRepository.save(
            User(
                username = request.username,
                job = request.job,
                jobDuration = request.jobDuration,
                accountId = request.accountId,
                password = passwordEncoder.encode(request.password),
                joinDate = LocalDateTime.now()
            )
        )
    }

    fun queryUserInfo(): UserInfoResponse {
        val user = SecurityUtil.getCurrentUser()

        return UserInfoResponse(
            username = user.username,
            joinDate = user.joinDate.toLocalDate(),
            coin = user.coin,
            job = user.job,
            jobDuration = user.jobDuration
        )
    }

    @Transactional
    fun updateUserInfo(request: UpdateUserInfoRequest) {
        val user = SecurityUtil.getCurrentUser()

        user.updateInfo(request.username, request.job, request.jobDuration)

        userRepository.save(user)
    }
}
