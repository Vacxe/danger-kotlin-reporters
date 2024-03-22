package io.github.vacxe.danger.kotlin.core.model

data class Finding(
    val file: String,
    val line: Int,
    val level: Level,
    val message: String
)
