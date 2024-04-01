package kr.hs.dsm.devlib.global.security.principle

import kr.hs.dsm.devlib.domain.book.persistence.repository.BookRepository
import kr.hs.dsm.devlib.domain.user.persistence.repository.UserRepository
import kr.hs.dsm.devlib.global.security.exception.InvalidTokenException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = username?.let { userRepository.findByIdOrNull(it.toLong()) }
        user.apply {
            if (this == null) {
                throw InvalidTokenException
            } else {
                return CustomDetailsImpl(user = this)
            }
        }
    }
}
