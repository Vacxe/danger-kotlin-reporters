package io.github.vacxe.danger.kotlin.core.reporter

import io.github.vacxe.danger.kotlin.core.model.Finding

interface FindingsDangerReporter {

    /**
     * Reports a [finding] found by plugins.
     */
    fun report(finding: Finding, inline: Boolean)
}
