package kr.hs.dsm.devlib.domain.question.presentation

import kr.hs.dsm.devlib.domain.question.presentation.dto.request.CreateQuestionRequest
import kr.hs.dsm.devlib.domain.question.presentation.dto.request.UpdateQuestionRequest
import kr.hs.dsm.devlib.domain.question.presentation.dto.response.QueryQuestionInfoResponse
import kr.hs.dsm.devlib.domain.question.presentation.dto.response.QueryQuestionListResponse
import kr.hs.dsm.devlib.domain.question.service.CreateQuestionService
import kr.hs.dsm.devlib.domain.question.service.DeleteQuestionService
import kr.hs.dsm.devlib.domain.question.service.QueryQuestionInfoService
import kr.hs.dsm.devlib.domain.question.service.QueryQuestionListService
import kr.hs.dsm.devlib.domain.question.service.UpdateQuestionService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import org.springframework.web.bind.annotation.RequestParam

@RestController
@RequestMapping("/question")
class QuestionController(
    private val createQuestionService: CreateQuestionService,
    private val deleteQuestionService: DeleteQuestionService,
    private val updateQuestionService: UpdateQuestionService,
    private val queryQuestionInfoService: QueryQuestionInfoService,
    private val queryQuestionListService: QueryQuestionListService
) {
    @PostMapping
    fun createQuestion(@RequestBody @Valid request: CreateQuestionRequest) {
        createQuestionService.execute(request)
    }

    @PatchMapping("/{question-id}")
    fun updateQuestion(
        @RequestBody @Valid request: UpdateQuestionRequest,
        @PathVariable("question-id") id: Long
    ) {
        updateQuestionService.execute(id, request)
    }

    @DeleteMapping("/{question-id}")
    fun deleteQuestion(@PathVariable("question-id") id: Long) {
        deleteQuestionService.execute(id)
    }

    @GetMapping("/{reply-id}")
    fun queryQuestionInfo(@PathVariable("reply-id") replyId: Long): QueryQuestionInfoResponse {
        return queryQuestionInfoService.execute(replyId)
    }

    @GetMapping
    fun questionQuestions(@RequestParam("title", required = false) title: String?): QueryQuestionListResponse {
        return queryQuestionListService.execute(title)
    }
}