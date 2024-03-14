package io.github.vacxe.danger.shellcheck.model

import com.google.gson.annotations.SerializedName

enum class Level {
    @SerializedName("error") ERROR,
    @SerializedName("warning") WARNING,
    @SerializedName("info") INFO
}
