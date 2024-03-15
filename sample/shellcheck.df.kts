@file:DependsOn("io.github.vacxe.danger.shellcheck:plugin:1.0.5")

import systems.danger.kotlin.*
import systems.danger.kotlin.models.github.*
import io.github.vacxe.danger.shellcheck.ShellcheckPlugin
import java.io.File

register.plugin(ShellcheckPlugin)

danger(args) {
    shellcheckReport()
}

fun shellcheckReport() {
    val shellcheckReportFile = File("sample/report.json")
    if (!shellcheckReportFile.exists()) {
        warn(
            "Shellcheck report not exist",
        )
    } else {
        ShellcheckPlugin.parseAndReport(shellcheckReportFile)
    }
}
