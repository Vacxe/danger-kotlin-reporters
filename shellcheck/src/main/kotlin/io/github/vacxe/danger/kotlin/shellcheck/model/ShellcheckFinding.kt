package io.github.vacxe.danger.kotlin.shellcheck.model

data class ShellcheckFinding(
    val file: String,
    val line: Int,
    val endLine: Int,
    val column: Int,
    val endColumn: Int,
    val level: Level,
    val code: Int,
    val message: String,
    val fix: Fix?,
)
