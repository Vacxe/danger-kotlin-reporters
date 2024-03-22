@file:Suppress("MemberVisibilityCanBePrivate")

package io.github.vacxe.danger.kotlin.shellcheck

import io.github.vacxe.danger.kotlin.core.DangerReporter
import io.github.vacxe.danger.kotlin.shellcheck.parser.ShellcheckReportParser
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
object ShellcheckPlugin : DangerReporter() {

    override val id: String = "shellcheck-plugin"

    private val parser = ShellcheckReportParser()

    override fun parse(
        vararg files: File,
    ) = parser.parse(files.asList())
}
