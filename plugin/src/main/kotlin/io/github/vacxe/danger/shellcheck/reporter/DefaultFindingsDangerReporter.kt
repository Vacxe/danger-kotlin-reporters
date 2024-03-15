package io.github.vacxe.danger.shellcheck.reporter

import io.github.vacxe.danger.shellcheck.model.Finding
import io.github.vacxe.danger.shellcheck.model.Level
import systems.danger.kotlin.sdk.DangerContext

class DefaultFindingsDangerReporter(
    private val context: DangerContext,
    private val inline: Boolean,
) : FindingsDangerReporter {

    override fun report(
        finding: Finding,
    ) {
        val message = createMessage(finding)
        val severity = finding.level
        val file = finding.file
        val line = finding.line

        if (inline) {
            report(message, severity, file, line)
        } else {
            report(message, severity)
        }
    }

    private fun report(
        message: String,
        severity: Level,
    ) {
        when (severity) {
            Level.INFO, Level.STYLE -> context.message(message)
            Level.WARNING -> context.warn(message)
            Level.ERROR -> context.fail(message)
        }
    }

    private fun report(
        message: String,
        severity: Level,
        filePath: String,
        line: Int,
    ) {
        when (severity) {
            Level.INFO, Level.STYLE -> context.message(message, filePath, line)
            Level.WARNING -> context.warn(message, filePath, line)
            Level.ERROR -> context.fail(message, filePath, line)
        }
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
