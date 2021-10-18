package ru.jb.po.generator

import ru.jb.po.config.RandomConfig
import ru.jb.po.config.YoutrackConfig
import ru.youtrack.api.DefaultApi
import ru.youtrack.invoker.ApiClient
import ru.youtrack.model.IssueComment
import java.time.LocalDateTime
import kotlin.random.Random

class CommentsGenerator {
    val apiClient = ApiClient()

    init {
        apiClient.basePath = YoutrackConfig.URL.value
        apiClient.setBearerToken(YoutrackConfig.TOKEN.value)
    }

    val api = DefaultApi(apiClient)

    fun run() {
        val issues = api.issuesGet(null, "id", null, null)
        val now = LocalDateTime.now()
        val count = Random.nextInt(RandomConfig.MAX_COMMENTS.value)
        for (i in 0..count step 1) {
            val issue = issues[Random.nextInt(issues.size)]
            val comment = IssueComment()
            comment.text = "Comment $i $now"
            api.issuesIdCommentsPost(issue.id, null, null, comment)
        }
    }

}
