/*
 * Copyright 2026 joganubaid
 *
 * Cryptomator Morphe Patches are licensed under the GNU General Public License v3.0,
 * with additional terms under GPLv3 Section 7. See LICENSE for details.
 */

package app.morphe.patches.cryptomator

import app.morphe.patcher.extensions.InstructionExtensions.addInstruction
import app.morphe.patcher.patch.bytecodePatch

/**
 * Check License No-Op
 *
 * Makes VaultListPresenter.checkLicense() a no-op to skip license check entirely.
 *
 * Verified target via jadx decompilation:
 *   VaultListPresenter.checkLicense() → void (private final)
 *
 * Strategy: Replace entire method body with immediate return-void.
 * This prevents the license check UI flow from ever triggering.
 */
val checkLicenseNoOpPatch = bytecodePatch(
    name = "Check License No-Op",
    description = "Makes VaultListPresenter.checkLicense() a no-op to skip license check",
    default = true,
) {
    dependsOn(bytecodePatch(Fingerprints.vaultListPresenterCheckLicense) {
        execute {
            // Replace entire method body with immediate return
            method.implementation!!.clearInstructions()
            method.implementation!!.addInstruction("return-void")
        }
    })
}