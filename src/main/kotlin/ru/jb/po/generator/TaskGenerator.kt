package ru.jb.po.generator

import ru.jb.po.config.RandomConfig
import ru.youtrack.api.DefaultApi
import ru.youtrack.model.Issue
import java.time.LocalDateTime
import java.util.logging.Logger
import kotlin.random.Random

class TaskGenerator(val api: DefaultApi) {
    private val logger = Logger.getLogger(this.javaClass.name)

    fun run() {
        val projects = api.adminProjectsGet("id,name", null, null)
        val now = LocalDateTime.now()
        val count = Random.nextInt(RandomConfig.MAX_TASKS.value)
        logger.info { "Task generator started at $now" }
        for (i in 0..count step 1) {
            val issue = Issue()
            issue.project = projects[Random.nextInt(projects.size)]
            issue.summary = "Summary $now"
            issue.description = "Description $i $now"
            api.issuesPost(null, null, issue)
        }
        logger.info { "Task generator started at ${LocalDateTime.now()}" }
    }
}