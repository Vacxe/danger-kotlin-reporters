package io.github.vacxe.danger.kotlin.detekt

interface FindingFilePathMapper {
    fun map(input: String): String
}
