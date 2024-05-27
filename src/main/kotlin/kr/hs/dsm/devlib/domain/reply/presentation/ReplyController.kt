package kr.hs.dsm.devlib.domain.reply.presentation

import kr.hs.dsm.devlib.domain.reply.presentation.dto.request.CreateReplyRequest
import kr.hs.dsm.devlib.domain.reply.presentation.dto.request.UpdateReplyRequest
import kr.hs.dsm.devlib.domain.reply.service.ReplyService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/reply")
@RestController
class ReplyController(
    private val replyService: ReplyService
) {
    @PostMapping("/{question-id}")
    fun create(@PathVariable("question-id") questionId: Long, @RequestBody @Valid request: CreateReplyRequest) {
        replyService.create(questionId, request)
    }

    @PatchMapping("/reply-id")
    fun update(@PathVariable("reply-id") replyId: Long, @RequestBody @Valid request: UpdateReplyRequest) {
        replyService.update(replyId, request)
    }

    @DeleteMapping("/{reply-id}")
    fun delete(@PathVariable("reply-id") replyId: Long) {
        replyService.delete(replyId)
    }
}