package org.cryptomator.patches

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import org.cryptomator.patches.Constants.COMPATIBILITY_CRYPTOMATOR_1_12_3

private const val PATCHED_LICENSE_CHECK_CLASS = "Lorg/cryptomator/domain/usecases/PatchedLicenseCheck;"

/**
 * Patches Cryptomator to bypass license verification.
 * This patch modifies:
 * 1. DoLicenseCheck.execute() - Returns a valid LicenseCheck without JWT verification
 * 2. VaultListPresenter.checkLicense() - No-op to prevent license check on startup
 */
@Suppress("unused")
val licenseBypassPatch = bytecodePatch(
    name = "License Bypass",
    description = "Bypasses Cryptomator license verification to enable premium features",
    default = true
) {
    compatibleWith(COMPATIBILITY_CRYPTOMATOR_1_12_3)

    // Patch 1: Modify DoLicenseCheck.execute() to return PatchedLicenseCheck
    execute {
        DoLicenseCheckExecuteFingerprint.method.addInstructions(
            0,
            """
                # Create PatchedLicenseCheck instance
                new-instance v0, $PATCHED_LICENSE_CHECK_CLASS
                invoke-direct {v0}, $PATCHED_LICENSE_CHECK_CLASS;-><init>()V
                return-object v0
            """
        )
    }
}

/**
 * Patch to make VaultListPresenter.checkLicense() a no-op
 * This prevents the license check from running on app startup
 */
@Suppress("unused")
val checkLicenseNoOpPatch = bytecodePatch(
    name = "Check License No-Op",
    description = "Makes VaultListPresenter.checkLicense() a no-op to skip license check",
    default = true
) {
    compatibleWith(COMPATIBILITY_CRYPTOMATOR_1_12_3)

    execute {
        VaultListPresenterCheckLicenseFingerprint.method.addInstructions(
            0,
            """
                # No-op - return immediately without checking license
                return-void
            """
        )
    }
}