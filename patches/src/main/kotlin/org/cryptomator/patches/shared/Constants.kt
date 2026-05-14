package org.cryptomator.patches.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

internal object Constants {
    val COMPATIBILITY_CRYPTOMATOR = Compatibility(
        name = "Cryptomator",
        packageName = "org.cryptomator",
        apkFileType = ApkFileType.APK_REQUIRED,
        appIconColor = 0x4758A0,
        signatures = setOf(
            "f7c3ec3b0d588d3cb52983e9eb1a7421c93d4339a286398e71d7b651e8d8ecdd"
        ),
        targets = listOf(
            AppTarget(
                version = "1.12.3",
                minSdk = 26
            )
        )
    )
}
