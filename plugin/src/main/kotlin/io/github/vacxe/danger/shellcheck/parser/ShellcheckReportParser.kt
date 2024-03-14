package io.github.vacxe.danger.shellcheck.parser

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.vacxe.danger.shellcheck.model.Finding
import java.io.File
import java.lang.reflect.Type

internal class ShellcheckReportParser {

    fun parse(files: List<File>): List<Finding> {
        return files
            .map(::parse)
            .flatten()
    }

    private fun parse(file: File): List<Finding> {
        return file.readText().let {
            val listType: Type = object : TypeToken<ArrayList<Finding?>?>() {}.type
            Gson().fromJson(it, listType)
        } ?: emptyList()
    }
}
