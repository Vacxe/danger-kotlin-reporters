@file:Repository("https://s01.oss.sonatype.org/content/repositories/snapshots")
@file:Repository("https://s01.oss.sonatype.org/content/groups/public/")
@file:DependsOn("io.github.vacxe.danger.kotlin:shellcheck:1.0.1-SNAPSHOT")

import systems.danger.kotlin.*
import systems.danger.kotlin.models.github.*
import io.github.vacxe.danger.kotlin.shellcheck.ShellcheckPlugin
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
