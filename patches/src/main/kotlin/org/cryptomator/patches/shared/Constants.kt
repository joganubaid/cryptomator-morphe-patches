package org.cryptomator.patches.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

internal object Constants {
    private val APP_ICON_COLOR_HEX = Regex("^[0-9A-F]{6}$")

    private fun normalizeAppIconColor(color: String): String {
        val normalized = color
            .trim()
            .removePrefix("#")
            .removePrefix("0x")
            .removePrefix("0X")
            .uppercase()

        require(normalized.matches(APP_ICON_COLOR_HEX)) {
            "App icon color must contain 6 hex digits (RRGGBB): $color"
        }

        return "0x$normalized"
    }

    val COMPATIBILITY_CRYPTOMATOR = Compatibility(
        name = "Cryptomator",
        packageName = "org.cryptomator",
        apkFileType = ApkFileType.APK_REQUIRED,
        appIconColor = normalizeAppIconColor("0x4758A0"),
        // Replace with the actual SHA-256 certificate fingerprint of the Cryptomator APK.
        signatures = setOf("placeholder"),
        targets = listOf(
            AppTarget(
                version = "1.12.3",
                minSdk = 26
            )
        )
    )
}
