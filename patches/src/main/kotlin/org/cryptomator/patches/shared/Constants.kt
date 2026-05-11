package org.cryptomator.patches.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

internal object Constants {
    val COMPATIBILITY_CRYPTOMATOR = Compatibility(
        name = "Cryptomator",
        packageName = "org.cryptomator",
        apkFileType = ApkFileType.APK_REQUIRED,
        appIconColor = "0x4758A0",
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
