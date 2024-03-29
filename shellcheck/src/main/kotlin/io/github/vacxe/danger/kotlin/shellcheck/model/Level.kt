package io.github.vacxe.danger.kotlin.shellcheck.model

import com.google.gson.annotations.SerializedName

enum class Level {
    @SerializedName("error")
    ERROR,
    @SerializedName("style")
    STYLE,
    @SerializedName("warning")
    WARNING,
    @SerializedName("info")
    INFO
}
