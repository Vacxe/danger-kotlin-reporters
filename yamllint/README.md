# Yaml Plugin for Danger Kotlin

Plugin for [danger/kotlin](https://github.com/danger/kotlin) which helps to parse and report [Yamllint](https://yamllint.readthedocs.io/en/stable/#/) violations from its output report file.

[![Current version](https://img.shields.io/badge/io.github.vacxe.danger.kotlin:yamllint-1.0.2-orange)](https://central.sonatype.com/artifact/io.github.vacxe.danger.kotlin/yamllint)

## Usage

`Dangerfile.df.kts` is the main configuration file of any **danger/kotlin** setup. To use this plugin you should add it as a dependency on top of this file and call register.
Latest version could be found in [Maven Central](https://search.maven.org/artifact/io.github.vacxe.danger.koltin/yamllint)
```kotlin
@file:DependsOn("io.github.vacxe.danger.kotlin:yamllint:<LATEST VERSION>")

register.plugin(YamllintPlugin)
```

If you have custom Maven proxy like Artifactory don't forget to add it

```
@file:Repository("https://URL")
@file:DependsOn("io.github.vacxe.danger.kotlin:yamllint:<LATEST VERSION>", options = ["username=<ARTIFACTORY_USERNAME>", "password=<ARTIFACTORY_PASSWORD>"])
```

### Basic

#### Single file parse and report

This does what it says. If you have one Yamllint report and don't want any customization - that's probably your choice.

```kotlin

```

By default, Plugin inline comments for you pull request and apply severity level based on Yamllint report

```kotlin
YamllintPlugin.parseAndReport(reportFile, inline = false)
```

#### Multiple files parse and report

Actually parameters of all `parse` functions are `varargs`, so you could provide it as many report files as you want.

```kotlin
YamllintPlugin.parseAndReport(reportFile1, reportFile2, reportFile3)
```

or

```kotlin
val files: Array<File> = findReportFilesByYourself()
YamllintPlugin.parseAndReport(*files)
```

### Parse

You could also parse files without immediate reporting.

```kotlin
val findings: List<Findings> = YamllintPlugin.parse(files)
```

### Report

You could also report it like this

```kotlin
YamllintPlugin.report(findings)
```

## Example

```kotlin
@file:DependsOn("io.github.vacxe.danger.kotlin:yamllint:<LATEST VERSION>")

import systems.danger.kotlin.*
import systems.danger.kotlin.models.github.*
import io.github.vacxe.danger.kotlin.yamllint.YamllintPlugin
import java.io.File

register.plugin(YamllintPlugin)

danger(args) {
    yamllintReport()
}

fun yamllintReport() {
    val yamllintReportFile = File("yamllint/report")
    if (!yamllintReportFile.exists()) {
        warn(
            "Yamllint report not exist",
        )
    } else {
        YamllintPlugin.parseAndReport(yamllintReportFile)
    }
}
```
