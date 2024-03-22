package io.github.vacxe.danger.kotlin.shellcheck.parser

import io.github.vacxe.danger.kotlin.core.model.Finding
import io.github.vacxe.danger.kotlin.core.model.Level
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class YamllintReportParserTest {
    private val yamllintReportParser = YamllintReportParser()

    @Test
    fun testReportParse(){
        val input = "MyClass/test.yaml:1:2: [error] syntax error: expected <block end>, but found '<block mapping start>' (syntax)>"
        val result = yamllintReportParser.map(input)

        val expected = Finding("MyClass\\test.yaml", 1, Level.ERROR, "\n" +
            "**Yamllint**: Syntax error: expected <block end>, but found '<block mapping start>' (syntax)>")

        assertEquals(expected, result)
    }
}
