@file:Suppress("MemberVisibilityCanBePrivate")

package io.github.vacxe.danger.kotlin.yamllint

import io.github.vacxe.danger.kotlin.core.DangerReporter
import io.github.vacxe.danger.kotlin.core.reporter.DefaultFindingsDangerReporter
import io.github.vacxe.danger.kotlin.core.reporter.FindingsDangerReporter
import io.github.vacxe.danger.kotlin.yamllint.parser.YamllintReportParser
import systems.danger.kotlin.sdk.DangerPlugin
import java.io.File

/**
 * Yamllint artifacts parse and report plugin to use in Dangerfiles
 *
 * Usage:
 * ```
 * val yamllintPlugin = YamllintPlugin()
 * register.plugin(yamllintPlugin)
 * val report = yamllintPlugin.parse(files)
 * yamllintPlugin.report(report)
 * ```
 * or
 * ```
 * yamllintPlugin.parseAndReport(files)
 * ```
 *
 * @param findingFilePathMapper custom mapper for reported file path
 */
class YamllintPlugin(findingFilePathMapper: (String) -> String = { input -> input }) : DangerPlugin(), DangerReporter {

    override val id: String = "yamllint-plugin"

    private val parser = YamllintReportParser(findingFilePathMapper)

    override fun parse(
        vararg files: File,
    ) = parser.parse(files.asList())

    override val findingsDangerReporter: FindingsDangerReporter
        get() = DefaultFindingsDangerReporter(context)
}
