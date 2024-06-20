package io.github.vacxe.danger.kotlin.detekt.parser

import io.github.vacxe.danger.kotlin.core.model.Finding
import io.github.vacxe.danger.kotlin.core.model.Level
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DetektReportParserTest {
    private val detektReportParser = DetektReportParser()

    @Test
    fun testReportParse() {
        val input = """
            <?xml version="1.0" encoding="UTF-8"?>
            <checkstyle version="4.3">
                <file name="MyClass.kt">
                    <error line="1" column="2" severity="warning" message="Warning" source="detekt.Warning"/>
                    <error line="3" column="4" severity="error" message="Error" source="detekt.Error"/>
                </file>
                <file name="MyClass2.kt">
                    <error line="1" column="2" severity="warning" message="Warning" source="detekt.Warning"/>
                    <error line="3" column="4" severity="error" message="Error" source="detekt.Error"/>
                </file>
            </checkstyle>
            """.trimIndent()

        val result = detektReportParser.map(input)

        assertEquals(
            listOf(
                Finding("MyClass.kt", 1, Level.WARNING, "\n" + "**Detekt**: Warning"),
                Finding("MyClass.kt", 3, Level.ERROR, "\n" + "**Detekt**: Error"),
                Finding("MyClass2.kt", 1, Level.WARNING, "\n" + "**Detekt**: Warning"),
                Finding("MyClass2.kt", 3, Level.ERROR, "\n" + "**Detekt**: Error"),
            ),
            result,
        )
    }
}
