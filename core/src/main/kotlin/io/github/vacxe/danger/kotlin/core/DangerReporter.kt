package io.github.vacxe.danger.kotlin.core

import io.github.vacxe.danger.kotlin.core.model.Finding
import io.github.vacxe.danger.kotlin.core.reporter.FindingsDangerReporter
import java.io.File

interface DangerReporter {
    fun parse(vararg files: File): List<Finding>

    val findingsDangerReporter: FindingsDangerReporter

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
