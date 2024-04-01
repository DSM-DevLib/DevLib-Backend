package kr.hs.dsm.devlib.domain.user.presentation

import javax.validation.Valid
import kr.hs.dsm.devlib.common.dto.TokenResponse
import kr.hs.dsm.devlib.domain.user.presentation.dto.*
import kr.hs.dsm.devlib.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/user")
@RestController
class UserController(
    private val userService: UserService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun userSignUp(@Valid @RequestBody request: UserRequest) {
        userService.signUp(request)
    }

    @PostMapping("/auth")
    fun userSignIn(@Valid @RequestBody request: UserRequest): TokenResponse =
        userService.signIn(request)

    @GetMapping
    fun queryUserInfo(): UserInfoResponse =
        userService.queryUserInfo()
}