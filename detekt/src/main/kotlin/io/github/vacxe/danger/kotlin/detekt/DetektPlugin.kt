@file:Suppress("MemberVisibilityCanBePrivate")

package io.github.vacxe.danger.kotlin.detekt

import io.github.vacxe.danger.kotlin.core.DangerReporter
import io.github.vacxe.danger.kotlin.core.reporter.DefaultFindingsDangerReporter
import io.github.vacxe.danger.kotlin.core.reporter.FindingsDangerReporter
import io.github.vacxe.danger.kotlin.detekt.parser.DetektReportParser
import systems.danger.kotlin.sdk.DangerPlugin
import java.io.File

/**
 * Detekt artifacts parse and report plugin to use in Dangerfiles
 *
 * Usage:
 * ```
 * val detektPlugin = DetektPlugin()
 * register.plugin(detektPlugin)
 * val report = detektPlugin.parse(files)
 * detektPlugin.report(report)
 * ```
 * or
 * ```
 * detektPlugin.parseAndReport(files)
 * ```
 *
 * @param findingFilePathMapper custom mapper for reported file path
 */
class DetektPlugin(findingFilePathMapper: (String) -> String = { input -> input }) : DangerPlugin(), DangerReporter {

    override val id: String = "detekt-plugin"

    private val parser = DetektReportParser(findingFilePathMapper)

    override fun parse(
        vararg files: File,
    ) = parser.parse(files.asList())

    override val findingsDangerReporter: FindingsDangerReporter
        get() = DefaultFindingsDangerReporter(context)
}
