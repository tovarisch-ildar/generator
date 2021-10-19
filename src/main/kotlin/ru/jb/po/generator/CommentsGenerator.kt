package ru.jb.po.generator

import ru.jb.po.config.RandomConfig
import ru.youtrack.api.DefaultApi
import ru.youtrack.model.IssueComment
import java.time.LocalDateTime
import java.util.logging.Logger
import kotlin.random.Random

class CommentsGenerator(val api: DefaultApi) {
    private val logger = Logger.getLogger(this.javaClass.name)

    fun run() {
        val issues = api.issuesGet(null, "id", null, null)
        val now = LocalDateTime.now()
        logger.info { "Comments generator started at $now" }
        val count = Random.nextInt(RandomConfig.MAX_COMMENTS.value)
        for (i in 0..count step 1) {
            val issue = issues[Random.nextInt(issues.size)]
            val comment = IssueComment()
            comment.text = "Comment $i $now"
            api.issuesIdCommentsPost(issue.id, null, null, comment)
        }
        logger.info { "Comments generator finished at ${LocalDateTime.now()}" }
    }

}
