package io.github.vacxe.danger.kotlin.yamllint.parser

import io.github.vacxe.danger.kotlin.core.model.Finding
import io.github.vacxe.danger.kotlin.core.model.Level
import java.io.File
import java.util.*

typealias LevelCore = io.github.vacxe.danger.kotlin.core.model.Level

internal class YamllintReportParser(private val findingFilePathMapper: (String) -> String = { input -> input }) {

    private val pathPrefix = File("").absolutePath

    fun parse(files: List<File>): List<Finding> {
        return files
            .map(File::readLines)
            .flatten()
            .map(::map)
    }

    fun map(input: String): Finding {
        val location = input.substringBefore(" ")
        val content = input.substringAfter(" ")
        val file = location.substringBefore(":")
            .let(findingFilePathMapper)
        val line = location
            .substringAfter(":")
            .substringBefore(":")
            .toInt()
        val level = content.substringBefore(" ").let {
            when(it){
                "[error]" -> LevelCore.ERROR
                "[warning]" -> LevelCore.WARNING
                else -> LevelCore.MESSAGE
            }
        }
        val message = content
            .substringAfter(" ")
            .replaceFirstChar {
                if (it.isLowerCase())
                    it.titlecase(Locale.getDefault())
                else
                    it.toString() }
            .let(::createMessage)


        return Finding(file, line, level, message)
    }

    private fun createMessage(message: String): String {
        return listOfNotNull(
            "", // start message with blank line
            "**Yamllint**: $message",
        ).joinToString(separator = "\n")
    }
}
