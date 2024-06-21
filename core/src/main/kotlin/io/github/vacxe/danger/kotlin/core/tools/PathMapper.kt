package io.github.vacxe.danger.kotlin.core.tools

import java.io.File

object PathMapper {
    private val pathPrefix = File("").absolutePath

    fun removeAbsolutePathPrefix(input: String): String {
        val file = File(input)
        if (file.absolutePath == pathPrefix) return file.absolutePath
        return file.absolutePath.removePrefix(pathPrefix + File.separator)
    }
}
