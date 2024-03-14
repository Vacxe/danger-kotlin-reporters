package io.github.vacxe.danger.shellcheck.model

data class Finding(
    val file: String,
    val line: Int,
    val endLine: Int,
    val column: Int,
    val endColumn: Int,
    val level: Level,
    val code: Int,
    val message: String,
    val fix: List<Replacement>?
)
