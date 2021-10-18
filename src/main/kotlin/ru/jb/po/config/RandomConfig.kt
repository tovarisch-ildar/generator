package ru.jb.po.config

enum class RandomConfig (val value: Int) {

    MAX_TASKS(Integer.parseInt(System.getenv("MAX_TASKS"))),

    MAX_COMMENTS(Integer.parseInt(System.getenv("MAX_COMMENTS")))

}
