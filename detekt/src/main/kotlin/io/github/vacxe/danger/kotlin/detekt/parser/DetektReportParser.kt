package io.github.vacxe.danger.kotlin.detekt.parser

import io.github.vacxe.danger.kotlin.core.model.Finding
import io.github.vacxe.danger.kotlin.core.model.Level
import org.xml.sax.InputSource

import java.io.File
import java.io.StringReader
import java.util.*
import javax.lang.model.element.Element
import javax.xml.parsers.DocumentBuilderFactory
internal class DetektReportParser {

    private val pathPrefix = File("").absolutePath

    fun parse(files: List<File>): List<Finding> {
        return files
            .map(File::readText)
            .map(::map)
            .flatten()
    }

    fun map(input: String): List<Finding> {
        val findings = arrayListOf<Finding>()
        val builderFactory = DocumentBuilderFactory.newInstance()
        val docBuilder = builderFactory.newDocumentBuilder()
        val doc = docBuilder.parse(InputSource(StringReader(input)))
        doc.documentElement.normalize()
        val files = doc.getElementsByTagName("file")
        for (fileCounter in 0 until files.length) {
            val file = files.item(fileCounter)
            val fileName = file.attributes.getNamedItem("name").textContent
            for (errorCounter in 0 until file.childNodes.length) {
                val error = file.childNodes.item(errorCounter)
                try {
                    val line = error.attributes.getNamedItem("line").textContent
                    val severity = error.attributes.getNamedItem("severity").textContent
                    val message = error.attributes.getNamedItem("message").textContent
                    findings.add(
                        Finding(
                            file = fileName
                                .let(::File)
                                .let(::createFilePath),
                            line = line.toInt(),
                            level = when (severity) {
                                "error" -> Level.ERROR
                                "warning" -> Level.WARNING
                                else -> Level.MESSAGE
                            },
                            message = createMessage(message),
                        ),
                    )
                } catch (_: Exception) {

                }
            }
        }

        return findings
    }

    private fun createFilePath(file: File): String {
        if (file.absolutePath == pathPrefix) return file.absolutePath
        return file.absolutePath.removePrefix(pathPrefix + File.separator)
    }

    private fun createMessage(message: String): String {
        return listOfNotNull(
            "", // start message with blank line
            "**Detekt**: $message",
        ).joinToString(separator = "\n")
    }
}
