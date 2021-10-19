package ru.jb.po

import ru.jb.po.config.YoutrackConfig
import ru.jb.po.generator.CommentsGenerator
import ru.jb.po.generator.TaskGenerator
import ru.youtrack.api.DefaultApi
import ru.youtrack.invoker.ApiClient
import java.time.LocalDateTime
import java.util.logging.Logger

private val logger = Logger.getLogger("Main")

fun main() {

    logger.info { "PO generator started at ${LocalDateTime.now()}" }
    val apiClient = ApiClient()
    apiClient.basePath = YoutrackConfig.URL.value
    apiClient.setBearerToken(YoutrackConfig.TOKEN.value)

    val api = DefaultApi(apiClient)

    TaskGenerator(api).run()
    CommentsGenerator(api).run()
    logger.info { "PO generator finished at ${LocalDateTime.now()}" }

}