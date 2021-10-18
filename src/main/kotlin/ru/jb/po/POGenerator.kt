package ru.jb.po

import ru.jb.po.generator.CommentsGenerator
import ru.jb.po.generator.TaskGenerator

fun main() {
    TaskGenerator().run()
    CommentsGenerator().run()
}