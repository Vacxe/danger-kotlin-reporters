package io.github.vacxe.danger.shellcheck.reporter

import io.github.vacxe.danger.shellcheck.model.Finding
import io.github.vacxe.danger.shellcheck.model.Level
import systems.danger.kotlin.sdk.DangerContext
import java.io.File

class DefaultFindingsDangerReporter(
    private val context: DangerContext,
    private val inline: Boolean,
    private val findingsAsFails: Boolean,
) : FindingsDangerReporter {

    private val pathPrefix = File("").absolutePath

    override fun report(
        finding: Finding,
    ) {
        val message = createMessage(finding)
        val severity = finding.level
        val file = finding
            .file
            .removePrefix(".${File.separator}")
            .let(::File)
        val filePath = file.let(::createFilePath)
        val line = finding.line

        if (!inline || filePath == null) {
            report(message, severity)
            return
        }
        report(message, severity, filePath, line)
    }

    private fun report(
        message: String,
        severity: Level,
    ) {
        if (findingsAsFails) {
            context.fail(message)
        } else {
            when (severity) {
                Level.INFO, Level.STYLE -> context.message(message)
                Level.WARNING -> context.warn(message)
                Level.ERROR -> context.fail(message)
            }
        }
    }

    private fun report(
        message: String,
        severity: Level,
        filePath: String,
        line: Int,
    ) {
        if (findingsAsFails) {
            context.fail(message, filePath, line)
        } else {
            when (severity) {
                Level.INFO, Level.STYLE -> context.message(message, filePath, line)
                Level.WARNING -> context.warn(message, filePath, line)
                Level.ERROR -> context.fail(message, filePath, line)
            }
        }
    }

    private fun createFilePath(file: File): String? {
        if (file.absolutePath == pathPrefix) return null
        return file.absolutePath.removePrefix(pathPrefix + File.separator)
    }

    private fun createMessage(finding: Finding): String {
        val message = finding.message.let { "**Shellcheck**: $it" }
        val rule = finding.code.let { "**Code**: [$it](https://www.shellcheck.net/wiki/SC$it)" }
        return listOfNotNull(
            "", // start message with blank line
            message,
            rule,
        ).joinToString(separator = "\n")
    }
}

