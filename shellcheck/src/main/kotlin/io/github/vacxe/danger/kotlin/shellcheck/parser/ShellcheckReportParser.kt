package io.github.vacxe.danger.kotlin.shellcheck.parser

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.vacxe.danger.kotlin.core.model.Finding
import io.github.vacxe.danger.kotlin.shellcheck.model.Level
import io.github.vacxe.danger.kotlin.shellcheck.model.ShellcheckFinding

import java.io.File
import java.lang.reflect.Type

typealias LevelCore = io.github.vacxe.danger.kotlin.core.model.Level

internal class ShellcheckReportParser(private val findingFilePathMapper: (String) -> String = { input -> input }) {

    fun parse(files: List<File>): List<Finding> {
        return files
            .map(::parse)
            .flatten()
            .map {
                Finding(
                    it.file
                        .let(findingFilePathMapper),
                    it.line,
                    when (it.level) {
                        Level.ERROR -> LevelCore.ERROR
                        Level.STYLE, Level.INFO -> LevelCore.MESSAGE
                        Level.WARNING -> LevelCore.WARNING
                    },
                    createMessage(it),
                )
            }
    }

    private fun parse(file: File): List<ShellcheckFinding> {
        return file.readText().let {
            val listType: Type = object : TypeToken<ArrayList<ShellcheckFinding?>?>() {}.type
            Gson().fromJson(it, listType)
        } ?: emptyList()
    }

    private fun createMessage(finding: ShellcheckFinding): String {
        val message = finding.message.let { "**Shellcheck**: $it" }
        val rule = finding.code.let { "**Code**: [$it](https://www.shellcheck.net/wiki/SC$it)" }
        return listOfNotNull(
            "", // start message with blank line
            message,
            rule,
        ).joinToString(separator = "\n")
    }
}
