package io.github.vacxe.danger.kotlin.core

import io.github.vacxe.danger.kotlin.core.model.Finding
import io.github.vacxe.danger.kotlin.core.reporter.DefaultFindingsDangerReporter
import systems.danger.kotlin.sdk.DangerPlugin
import java.io.File

abstract class DangerReporter : DangerPlugin() {
    abstract fun parse(vararg files: File): List<Finding>

    open val findingsDangerReporter = DefaultFindingsDangerReporter(context)

    fun parseAndReport(
        vararg files: File,
        inline: Boolean = true,
    ) {
        val report = parse(*files)
        report(
            report,
            inline,
        )
    }

    fun report(
        findings: List<Finding>,
        inline: Boolean = true,
    ) {
        findings.forEach { finding ->
            findingsDangerReporter.report(finding, inline)
        }
    }
}
