package com.dv.apps.komic.domain.model

data class PreviewSettings(
    val verticalCount: Int,
    val horizontalCount: Int,
    val quality: Quality,
) {
    enum class Quality {
        HD,
        FULL_HD,
        TWO_K,
        FOUR_K
    }
}