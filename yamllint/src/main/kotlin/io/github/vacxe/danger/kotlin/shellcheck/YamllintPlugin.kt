@file:Suppress("MemberVisibilityCanBePrivate")

package io.github.vacxe.danger.kotlin.shellcheck

import io.github.vacxe.danger.kotlin.core.DangerReporter
import io.github.vacxe.danger.kotlin.shellcheck.parser.YamllintReportParser
import java.io.File

/**
 * Shellcheck artifacts parse and report plugin to use in Dangerfiles
 *
 * Usage:
 * ```
 * register.plugin(YamllintPlugin)
 * val report = YamllintPlugin.parse(files)
 * YamllintPlugin.report(report)
 * ```
 * or
 * ```
 * YamllintPlugin.parseAndReport(files)
 * ```
 */
object YamllintPlugin : DangerReporter() {

    override val id: String = "yamllint-plugin"

    private val parser = YamllintReportParser()

    override fun parse(
        vararg files: File,
    ) = parser.parse(files.asList())
}
