package io.github.vacxe.danger.kotlin.core.reporter

import io.github.vacxe.danger.kotlin.core.model.Finding
import io.github.vacxe.danger.kotlin.core.model.Level
import systems.danger.kotlin.sdk.DangerContext

class DefaultFindingsDangerReporter(
    private val context: DangerContext,
) : FindingsDangerReporter {

    override fun report(
        finding: Finding,
        inline: Boolean,
    ) {
        val (file, line, level, message) = finding

        if (inline) {
            report(message, level, file, line)
        } else {
            report(message, level)
        }
    }

    private fun report(
        message: String,
        severity: Level,
    ) {
        when (severity) {
            Level.MESSAGE -> context.message(message)
            Level.WARNING -> context.warn(message)
            Level.ERROR -> context.fail(message)
        }
    }

    private fun report(
        message: String,
        level: Level,
        filePath: String,
        line: Int,
    ) {
        when (level) {
            Level.MESSAGE -> context.message(message, filePath, line)
            Level.WARNING -> context.warn(message, filePath, line)
            Level.ERROR -> context.fail(message, filePath, line)
        }
    }
}
