package ru.jb.po.generator

import ru.jb.po.config.RandomConfig
import ru.jb.po.config.YoutrackConfig
import ru.youtrack.api.DefaultApi
import ru.youtrack.invoker.ApiClient
import ru.youtrack.model.Issue
import java.time.LocalDateTime
import kotlin.random.Random

class TaskGenerator {

    val apiClient: ApiClient = ApiClient()

    init {
        apiClient.basePath = YoutrackConfig.URL.value
        apiClient.setBearerToken(YoutrackConfig.TOKEN.value)
    }

    val api = DefaultApi(apiClient)

    fun run() {
        val projects = api.adminProjectsGet("id,name", null, null)
        val now = LocalDateTime.now()
        val count = Random.nextInt(RandomConfig.MAX_COMMENTS.value)
        for (i in 0..count step 1) {
            val issue = Issue()
            val nextInt = Random.nextInt(projects.size)
            println("ASD $nextInt")
            issue.project = projects[nextInt]
            issue.summary = "Summary $now"
            issue.description = "Description $i $now"
            api.issuesPost(null, null, issue)
        }
    }
}