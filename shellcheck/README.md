# Shellcheck Plugin for Danger Kotlin

Plugin for [danger/kotlin](https://github.com/danger/kotlin) which helps to parse and report [Shellcheck](https://www.shellcheck.net/) violations from its json report file.

[![Current version](https://img.shields.io/badge/io.github.vacxe.danger.kotlin:shellcheck-1.1.0-orange)](https://central.sonatype.com/artifact/io.github.vacxe.danger.kotlin/shellcheck)

## Usage

`Dangerfile.df.kts` is the main configuration file of any **danger/kotlin** setup. To use this plugin you should add it as a dependency on top of this file and call register.
Latest version could be found in [Maven Central](https://search.maven.org/artifact/io.github.vacxe.danger.koltin/shellcheck)
```kotlin
@file:DependsOn("io.github.vacxe.danger.kotlin:shellcheck:<LATEST VERSION>")

val plugin = ShellcheckPlugin()
register.plugin(plugin)

register.plugin(plugin)
```

If you have custom Maven proxy like Artifactory don't forget to add it

```
@file:Repository("https://URL")
@file:DependsOn("io.github.vacxe.danger.kotlin:shellcheck:<LATEST VERSION>", options = ["username=<ARTIFACTORY_USERNAME>", "password=<ARTIFACTORY_PASSWORD>"])
```

### Basic

#### Single file parse and report

This does what it says. If you have one Shellcheck report and don't want any customization - that's probably your choice.

```kotlin

```

By default, Plugin inline comments for you pull request and apply severity level based on Shellcheck report

```kotlin
plugin.parseAndReport(reportFile, inline = false)
```

#### Multiple files parse and report

Actually parameters of all `parse` functions are `varargs`, so you could provide it as many report files as you want.

```kotlin
plugin.parseAndReport(reportFile1, reportFile2, reportFile3)
```

or

```kotlin
val files: Array<File> = findReportFilesByYourself()
plugin.parseAndReport(*files)
```

### Parse

You could also parse files without immediate reporting.

```kotlin
val findings: List<Findings> = plugin.parse(files)
```

### Report

You could also report it like this

```kotlin
plugin.report(findings)
```

## Example

```kotlin
@file:DependsOn("io.github.vacxe.danger.kotlin:shellcheck:<LATEST VERSION>")

import systems.danger.kotlin.*
import systems.danger.kotlin.models.github.*
import io.github.vacxe.danger.kotlin.shellcheck.ShellcheckPlugin
import java.io.File

val plugin = ShellcheckPlugin()
register.plugin(plugin)

danger(args) {
    shellcheckReport()
}

fun shellcheckReport() {
    val shellcheckReportFile = File("shellcheck/report.json")
    if (!shellcheckReportFile.exists()) {
        warn(
            "Shellcheck report not exist",
        )
    } else {
        plugin.parseAndReport(shellcheckReportFile)
    }
}
```
