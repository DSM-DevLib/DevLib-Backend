package kr.hs.dsm.devlib.domain.reaction.presentation

import kr.hs.dsm.devlib.domain.reaction.service.ReactionService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/reaction")
@RestController
class ReactionController(
    private val reactionService: ReactionService
) {
    @PostMapping("/{reply-id}")
    fun create(@PathVariable("reply-id")replyId: Long) {
        reactionService.create(replyId)
    }

    @DeleteMapping("/{reply-id}")
    fun delete(@PathVariable("reply-id")replyId: Long) {
        reactionService.delete(replyId)
    }
}