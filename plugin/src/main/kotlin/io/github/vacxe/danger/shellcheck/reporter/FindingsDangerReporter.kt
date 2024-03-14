package io.github.vacxe.danger.shellcheck.reporter

import io.github.vacxe.danger.shellcheck.model.Finding

interface FindingsDangerReporter {

    /**
     * Reports a [finding] found by shellcheck.
     */
    fun report(finding: Finding)
}
