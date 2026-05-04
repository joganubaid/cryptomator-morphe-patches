package org.cryptomator.patches

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    const val APP_PACKAGE = "org.cryptomator"
    const val APP_VERSION = "1.12.3"

    val COMPATIBILITY = Compatibility(
        name = "Cryptomator",
        packageName = APP_PACKAGE,
        apkFileType = ApkFileType.APK_REQUIRED,
        appIconColor = 0xFF4758A0,
        signatures = setOf<String>(),
        targets = listOf(
            AppTarget(
                version = "1.12.3",
                minSdk = 26
            )
        )
    )
}