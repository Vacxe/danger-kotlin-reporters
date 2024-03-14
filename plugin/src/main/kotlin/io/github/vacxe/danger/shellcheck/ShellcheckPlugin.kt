@file:Suppress("MemberVisibilityCanBePrivate")

package io.github.vacxe.danger.shellcheck

import io.github.vacxe.danger.shellcheck.model.Finding
import io.github.vacxe.danger.shellcheck.parser.ShellcheckReportParser
import io.github.vacxe.danger.shellcheck.reporter.DefaultFindingsDangerReporter
import systems.danger.kotlin.sdk.DangerPlugin
import java.io.File

/**
 * Shellcheck artifacts parse and report plugin to use in Dangerfiles
 *
 * Usage:
 * ```
 * register.plugin(ShellcheckPlugin)
 * val report = ShellcheckPlugin.parse(files)
 * ShellcheckPlugin.report(report)
 * ```
 * or
 * ```
 * ShellcheckPlugin.parseAndReport(files)
 * ```
 */
object ShellcheckPlugin : DangerPlugin() {

    override val id: String = "shellcheck-plugin"

    private val parser = ShellcheckReportParser()

    fun parseAndReport(
        vararg files: File,
        inline: Boolean = true,
        findingsAsFails: Boolean = false,
    ) {
        val report = parse(*files)
        report(
            report,
            inline,
            findingsAsFails,
        )
    }

    fun parse(
        vararg files: File,
    ) = parser.parse(files.asList())

    fun report(
        report: List<Finding>,
        inline: Boolean = true,
        findingsAsFails: Boolean = false,
    ) {
        val reporter = DefaultFindingsDangerReporter(
            context = context,
            inline = inline,
            findingsAsFails = findingsAsFails,
        )
        report.forEach(reporter::report)
    }
}
