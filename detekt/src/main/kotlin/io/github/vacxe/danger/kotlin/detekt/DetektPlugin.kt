@file:Suppress("MemberVisibilityCanBePrivate")

package io.github.vacxe.danger.kotlin.detekt

import io.github.vacxe.danger.kotlin.core.DangerReporter
import io.github.vacxe.danger.kotlin.core.reporter.DefaultFindingsDangerReporter
import io.github.vacxe.danger.kotlin.core.reporter.FindingsDangerReporter
import io.github.vacxe.danger.kotlin.detekt.parser.DetektReportParser
import systems.danger.kotlin.sdk.DangerPlugin
import java.io.File

/**
 * Shellcheck artifacts parse and report plugin to use in Dangerfiles
 *
 * Usage:
 * ```
 * register.plugin(DetektPlugin)
 * val report = DetektPlugin.parse(files)
 * DetektPlugin.report(report)
 * ```
 * or
 * ```
 * DetektPlugin.parseAndReport(files)
 * ```
 */
object DetektPlugin : DangerPlugin(), DangerReporter{

    override val id: String = "detekt-plugin"

    private val parser = DetektReportParser()

    override fun parse(
        vararg files: File,
    ) = parser.parse(files.asList())

    override val findingsDangerReporter: FindingsDangerReporter
        get() = DefaultFindingsDangerReporter(context)
}
