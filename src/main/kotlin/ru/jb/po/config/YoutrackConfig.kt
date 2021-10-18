package ru.jb.po.config

enum class YoutrackConfig(val value: String) {

    URL(System.getenv("YOUTRACK_URL")),

    TOKEN(System.getenv("YOUTRACK_TOKEN"))

}
